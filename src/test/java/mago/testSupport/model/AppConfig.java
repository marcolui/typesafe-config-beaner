package mago.testSupport.model;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class AppConfig {
    private String appName;
    private String host;
    private Integer port;
    private List<String> reserved;
    private List<DatabaseConfig> databases;
    private List<String> tags;
    private String env;
    private List caches;
    private SystemConfig system;

    public String appName() {
        return appName;
    }

    public String hostname() {
        return host;
    }

    public Integer port() {
        return port;
    }

    public List<DatabaseConfig> databaseConfigs() {
        return databases;
    }

    public List<String> reserved() {
        return reserved;
    }

    public List<String> tags() {
        return tags;
    }

    public SystemConfig systemConfig() {
        return system;
    }
    public String env() {
        return env;
    }

    @Test
    public List<Map> caches() {
        return caches;
    }
}
