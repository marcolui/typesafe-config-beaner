package mago.config;

import mago.testSupport.model.AppConfig;
import mago.testSupport.model.DatabaseConfig;
import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static mago.testSupport.utils.BeanerTestAsserts.assertServerHostAndUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ListPropTest {
    static AppConfig config = new ConfigTestHelper().loadDevConfig(AppConfig.class);

    @Test
    public void canLoadListOfObjects() throws Exception {
        List<DatabaseConfig> actualDatabaseConfigs = config.getDatabases();
        assertThat(actualDatabaseConfigs, hasSize(2));

        assertServerHostAndUser(actualDatabaseConfigs.get(0), "server-1", 8765, "fairy");
        assertServerHostAndUser(actualDatabaseConfigs.get(1), "server-2", 8888, "tales");
    }

    @Test
    public void childAttributeOverridesParent() throws Exception {
        assertThat(config.getTags(), equalTo(asList("dev")));
    }

    @Test
    public void emptyArrayPropIsInitializedAsEmptyList() throws Exception {
        assertThat(config.getReserved(), equalTo(Collections.EMPTY_LIST));
    }
}
