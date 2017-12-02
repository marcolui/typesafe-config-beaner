package mago.config;

import mago.testSupport.model.AppConfig;
import mago.testSupport.model.DatabaseConfig;
import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static mago.testSupport.utils.BeanerTestAsserts.assertServerHostAndUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ListPropTest {
    private static AppConfig config = new ConfigTestHelper().loadDevConfig();
    private static AppConfig baseConfig = new ConfigTestHelper().loadBaseConfig();

    @Test
    public void canLoadListOfMaps() {
        List<Map> actualCacheConfigs = config.caches();
        assertThat(actualCacheConfigs, hasSize(2));

        assertThat(actualCacheConfigs.get(0).get("url"), equalTo("meh-1"));
        assertThat(actualCacheConfigs.get(0).get("size"), equalTo(4));
        assertThat(actualCacheConfigs.get(1).get("url"), equalTo("meh-2"));
        assertThat(actualCacheConfigs.get(1).get("size"), equalTo(80));
    }

    @Test
    public void canLoadListOfBeansIfBeanTypeIsSpecified() throws Exception {
        List<DatabaseConfig> actualDatabaseConfigs = config.databaseConfigs();
        assertThat(actualDatabaseConfigs, hasSize(2));

        assertServerHostAndUser(actualDatabaseConfigs.get(0), "server-1", 8765, "fairy");
        assertServerHostAndUser(actualDatabaseConfigs.get(1), "server-2", 8888, "tales");
    }

    @Test
    public void childListPropOverridesParent() {
        assertThat(config.tags(), equalTo(asList("playground", "cool")));
        assertThat(baseConfig.tags(), equalTo(asList("awesome")));
    }

    @Test
    public void emptyArrayPropIsInitializedAsEmptyList() {
        assertThat(config.reserved(), equalTo(Collections.EMPTY_LIST));
    }
}
