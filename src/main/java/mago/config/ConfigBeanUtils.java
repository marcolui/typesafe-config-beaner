package mago.config;

import com.typesafe.config.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mago.config.ReflectionUtils.*;
import static mago.config.TypeSafeConfigUtils.configKeyToCamelCase;

public final class ConfigBeanUtils {
    public final static <T> T toBean(Config config, Class<?> clazz) { return toBean(config.root(), clazz); }

    public final static <T> T toBean(ConfigObject conf, Class<?> clazz) {
        try {
            final T result = (T) clazz.newInstance();
            for (Map.Entry<String, ConfigValue> entry : conf.entrySet()) {
                handle(result, entry.getKey(), entry.getValue());
            }
            return result;
        } catch (InstantiationException e) {
            throw new ConfigException("Unable to create configuration bean", e);
        } catch (IllegalAccessException e) {
            throw new ConfigException("Unable to set field on bean", e);
        } catch (NoSuchFieldException e) {
            throw new ConfigException("Config field not found on bean", e);
        }
    }

    private static <T> void handle(T fieldOwner, String key, ConfigValue value) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        String fieldName = configKeyToCamelCase(key);
        if (value.valueType() == ConfigValueType.LIST) {
            handleList(fieldOwner, fieldName, (ConfigList)value);
        } else if (value.valueType() == ConfigValueType.OBJECT) {
            handleMap(fieldOwner, fieldName, (ConfigObject)value);
        } else {
            setField(fieldOwner, fieldName, value.unwrapped());
        }
    }

    private static <T> void handleList(T fieldOwner, String fieldName, ConfigList value) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        List resultList = new ArrayList();
        setField(fieldOwner, fieldName, resultList);
        Class fieldGenericType = getFieldGenericType(fieldOwner, fieldName);
        for (ConfigValue val : value) {
            resultList.add((val.valueType() == ConfigValueType.OBJECT && fieldGenericType != null) ? mapToObject(fieldGenericType, (ConfigObject)val) : val.unwrapped());
        }
    }

    private static <T> void handleMap(T fieldOwner, String fieldName, ConfigObject value) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        if (!hasField(fieldOwner, fieldName)) {
            throw new NoSuchFieldException(fieldName);
        }

        Class targetFieldType = getFieldType(fieldOwner, fieldName);
        if (targetFieldType == ConfigObject.class) {
            setField(fieldOwner, fieldName, value);
        } else if (targetFieldType == Map.class) {
            Class mapValueGenericType = getFieldGenericType(fieldOwner, fieldName, 1);
            if (mapValueGenericType != null && isMapOfMaps(value)) {
                Map newValue = cloneMapWithChildMapsDeserialized(value, mapValueGenericType);
                setField(fieldOwner, fieldName, newValue);
            } else {
                setField(fieldOwner, fieldName, value.unwrapped());
            }
        } else {
            setField(fieldOwner, fieldName, mapToObject(targetFieldType, value));
        }
    }

    private static boolean isMapOfMaps(Map map) {
        return map.size() > 0 && map.values().iterator().next() instanceof Map;
    }

    private static Map cloneMapWithChildMapsDeserialized(ConfigObject mapToClone, Class childMapClazz) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Map newValue = new HashMap<>();
        for (Map.Entry<String, ConfigValue> entry : mapToClone.entrySet()) {
            newValue.put(entry.getKey(), mapToObject(childMapClazz, (ConfigObject)entry.getValue()));
        }
        return newValue;
    }

    private static <T> T mapToObject(Class clazz, ConfigObject map) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Object obj = clazz.newInstance();
        for (Map.Entry<String, ConfigValue> entry : map.entrySet()) {
            handle(obj, entry.getKey(), entry.getValue());
        }
        return (T) obj;
    }
}
