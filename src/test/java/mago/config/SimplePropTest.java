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
    public void parentPropIsInherited() {
        assertThat(config.appName(), equalTo("Everything is awesome"));
        assertThat(baseConfig.appName(), equalTo("Everything is awesome"));
    }

    @Test
    public void childCanHaveItsOwnProp() {
        assertThat(config.hostname(), equalTo("http://aweful.indeed"));
        assertThat(config.port(), equalTo(5354));
        assertThat(baseConfig.hostname(), isEmptyOrNullString());
        assertThat(baseConfig.port(), equalTo(null));
    }

    @Test
    public void canUseGetterToManipulateProp() {
        assertThat(config.databaseConfigs().get(0).password(), equalTo("dumbly-encrypted"));
    }

    @Test
    public void childPropOverridesParentProp() {
        assertThat(config.env(), equalTo("dev"));
        assertThat(baseConfig.env(), equalTo("all"));
    }
}