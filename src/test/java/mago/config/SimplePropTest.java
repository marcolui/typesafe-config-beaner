package mago.config;

import mago.testSupport.model.AppConfig;
import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePropTest {
    static AppConfig config = new ConfigTestHelper().loadDevConfig(AppConfig.class);

    @Test
    public void parentPropIsInherited() throws Exception {
        assertThat(config.getAppName(), equalTo("Everything is awesome"));
    }

    @Test
    public void canLoadPropFromChild() {
        assertThat(config.getHostName(), equalTo("http://aweful.indeed"));
        assertThat(config.getPort(), equalTo(5354));
    }


}