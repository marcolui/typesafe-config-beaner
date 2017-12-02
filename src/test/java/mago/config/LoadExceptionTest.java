package mago.config;

import mago.testSupport.utils.ConfigTestHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;

public class LoadExceptionTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void fieldDefinedInConfigFileMustExistInTheBean() {
        expectedException.expect(ConfigException.class);
        expectedException.expectMessage(is("Config field not found on bean: junkField"));
        new ConfigTestHelper().loadErrorConfig();
    }
}
