package yevgeniy.melnichuk.prettyconfiguration.model.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Property {
    private final String name;

    private String value;
    private Map<String, String> meta = new HashMap<>();

    public Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addMeta(String name, String value) {
        meta.put(name, value);
    }

    public Map<String, String> getMeta() {
        return Collections.unmodifiableMap(meta);
    }
}
