package mago.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigRenderOptions;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TypeSafeConfigUtils {
    private TypeSafeConfigUtils(){}

    public static Config loadConfig(String filenameWithoutExtension) {
        return ConfigFactory.parseResourcesAnySyntax(filenameWithoutExtension, ConfigParseOptions.defaults());
    }

    public static void dumpConfigToLog(Config config, PrintStream stream) {
        stream.println(config.root().render(ConfigRenderOptions.defaults().setOriginComments(false).setComments(false).setJson(false)));
    }

    private static Pattern p = Pattern.compile("-(.)");

    public static String configKeyToCamelCase(String configKey) {
        Matcher m = p.matcher(configKey);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
