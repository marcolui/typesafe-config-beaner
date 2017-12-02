package mago.testSupport.utils;

import mago.config.ConfigLoader;

public class ConfigTestHelper {
    public <T> T loadDevConfig(Class<T> configBeanClass) {
        return new ConfigLoader(() -> "dev").load("config-test-stuff/", configBeanClass);
    }
}
