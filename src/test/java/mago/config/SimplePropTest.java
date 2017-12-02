package mago.config;

import mago.testSupport.model.AppConfig;
import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class SimplePropTest {
    private static AppConfig config = new ConfigTestHelper().loadDevConfig();
    private static AppConfig baseConfig = new ConfigTestHelper().loadBaseConfig();

    @Test
    public void parentPropIsInherited() throws Exception {
        assertThat(config.getAppName(), equalTo("Everything is awesome"));
        assertThat(baseConfig.getAppName(), equalTo("Everything is awesome"));
    }

    @Test
    public void childCanHaveItsOwnProp() {
        assertThat(config.getHostName(), equalTo("http://aweful.indeed"));
        assertThat(config.getPort(), equalTo(5354));
        assertThat(baseConfig.getHostName(), isEmptyOrNullString());
        assertThat(baseConfig.getPort(), equalTo(null));
    }

    @Test
    public void childPropOverridesParentProp() throws Exception {
        assertThat(config.getEnv(), equalTo("dev"));
        assertThat(baseConfig.getEnv(), equalTo("all"));
    }
}