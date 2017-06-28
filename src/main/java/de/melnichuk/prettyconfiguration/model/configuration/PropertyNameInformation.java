package yevgeniy.melnichuk.prettyconfiguration.model.configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertyNameInformation {
    private static final Pattern PROPERTY_NAME_PATTERN = Pattern.compile("(?<name>.+)(?:\\._(?<meta>.+))");

    private final String name;
    private final String meta;

    public PropertyNameInformation(String name) {
        this.name = name;
        this.meta = null;
    }

    public PropertyNameInformation(String name, String meta) {
        this.name = name;
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public String getMeta() {
        return meta;
    }


    public static PropertyNameInformation extractFromPropertyName(String property) {
        Matcher matcher = PROPERTY_NAME_PATTERN.matcher(property);
        if (matcher.find()) {
            String name = matcher.group("name");
            String meta = matcher.group("meta");

            return new PropertyNameInformation(name, meta);
        } else {
            return new PropertyNameInformation(property);
        }
    }
}
