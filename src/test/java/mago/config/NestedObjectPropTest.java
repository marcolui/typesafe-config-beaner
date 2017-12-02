package mago.config;

import mago.testSupport.model.AppConfig;
import mago.testSupport.model.SystemConfig;
import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NestedObjectPropTest {
    private static AppConfig mergedConfig = new ConfigTestHelper().loadOverridenConfig();
    @Test
    public void nestedObjectPropsAreMergedAcrossMultipleLevel() {
        SystemConfig.ThreadPoolConfig threadPoolConfig = mergedConfig.systemConfig().threadPoolConfig();
        SystemConfig.LogConfig logConfig = mergedConfig.systemConfig().logConfig();
        assertThat(logConfig.name(), equalTo("dev"));
        assertThat(logConfig.rollSize(), equalTo(100000L));
        assertThat(threadPoolConfig.size(), equalTo(100));
    }
}
