package mago.config;

import com.typesafe.config.Config;

import static mago.config.ConfigBeanUtils.toBean;

public class ConfigLoader {
    private ConfigNameResolver configNameResolver;

    public ConfigLoader(ConfigNameResolver configNameResolver) {
        this.configNameResolver = configNameResolver;
    }

    public <T> T load(String folderOrPrefix, Class<? extends T> clazz) {
        final Config conf = load(folderOrPrefix);
        return toBean(conf, clazz);
    }

    public <T> T load(Class<? extends T> clazz) {
        return load("", clazz);
    }

    private Config load(String folderOrPrefix) {
        return TypeSafeConfigUtils.loadConfig(getConfigNameByHostName(folderOrPrefix)).resolve();
    }

    private String getConfigNameByHostName(String folderOrPrefix) {
        return folderOrPrefix + configNameResolver.resolve();
    }
}
