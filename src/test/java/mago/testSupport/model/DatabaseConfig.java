package mago.testSupport.model;

public class DatabaseConfig {
    private String host;
    private int port;
    private String user;
    private String password;

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public String user() {
        return user;
    }

    public String password() {
        return new StringBuffer(password).reverse().toString();
    }

}
