package mago.testSupport.utils;

import mago.config.ConfigLoader;
import mago.testSupport.model.AppConfig;

public class ConfigTestHelper {

    private static String TEST_CONFIG_DIRECTORY = "config-test-stuff/";

    public AppConfig loadBaseConfig() {
        return load("all");
    }
    public AppConfig loadDevConfig() {
        return load("dev");
    }
    public AppConfig loadOverridenConfig() {
        return load("overrides");
    }
    public AppConfig loadErrorConfig() {
        return load("error");
    }

    private AppConfig load(String configName) {
        return new ConfigLoader(() -> configName).load(TEST_CONFIG_DIRECTORY, AppConfig.class);
    }
}
