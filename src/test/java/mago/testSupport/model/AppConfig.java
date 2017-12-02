package mago.testSupport.model;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class AppConfig {
    private String appName;
    private String host;
    private Integer port;
    private List<String> reserved;
    List<DatabaseConfig> databases;
    List<String> tags;
    private String env;
    List caches;
    private SystemConfig system;

    public String getAppName() {
        return appName;
    }

    public String getHostName() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public List<DatabaseConfig> getDatabases() {
        return databases;
    }

    public List<String> getReserved() {
        return reserved;
    }

    public List<String> getTags() {
        return tags;
    }

    public SystemConfig systemConfig() {
        return system;
    }
    public String getEnv() {
        return env;
    }

    @Test
    public List<Map> getCaches() {
        return caches;
    }
}
