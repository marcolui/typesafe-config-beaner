package mago.testSupport.model;

import java.util.List;

public class AppConfig {
    private String appName;
    private String host;
    private int port;
    private List<String> reserved;
    List<DatabaseConfig> databases;
    List<String> tags;

    public String getAppName() {
        return appName;
    }

    public String getHostName() {
        return host;
    }

    public int getPort() {
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
}
