package mago.config;

import com.google.common.base.CaseFormat;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigRenderOptions;

import java.io.OutputStream;
import java.io.PrintStream;

public final class TypeSafeConfigUtils {
    private TypeSafeConfigUtils(){}

    public static Config loadConfig(String filenameWithoutExtension) {
        return ConfigFactory.parseResourcesAnySyntax(filenameWithoutExtension, ConfigParseOptions.defaults());
    }

    public static void dumpConfigToLog(Config config, PrintStream stream) {
        stream.println(config.root().render(ConfigRenderOptions.defaults().setOriginComments(false).setComments(false).setJson(false)));
    }

    public static String configKeyToCamelCase(String configKey) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, configKey.replaceAll("[.|-]", "_").toLowerCase());
    }
}
