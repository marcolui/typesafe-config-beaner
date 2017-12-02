package mago.testSupport.utils;

import mago.testSupport.model.DatabaseConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeanerTestAsserts {
    public static void assertServerHostAndUser(DatabaseConfig databaseConfig,
                                               String expectedServer, int expectedPort, String expectedUser) {
        assertThat(databaseConfig.getHost(), equalTo(expectedServer));
        assertThat(databaseConfig.getPort(), equalTo(expectedPort));
        assertThat(databaseConfig.getUser(), equalTo(expectedUser));
    }
}
