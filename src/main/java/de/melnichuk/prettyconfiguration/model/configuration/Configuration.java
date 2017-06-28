package yevgeniy.melnichuk.prettyconfiguration.model.configuration;

import java.util.*;

public class Configuration {
    private final String identifier;
    private final Map<String ,Property> properties = new TreeMap<>((a, b) -> a.compareTo(b));

    public Configuration(String identifier) {
        this.identifier = identifier;
    }

    public Collection<Property> getProperties() {
        return Collections.unmodifiableCollection(properties.values());
    }

    public Property getProperty(String name) {
        return properties.get(name);
    }

    public void addProperty(Property property) {
        properties.put(property.getName(), property);
    }

    public String getIdentifier() {
        return identifier;
    }

    /**
     * adds properties and meta information from mixin to this configuration. does not override existing properties or meta information.
     */
    public void applyMixin(Configuration mixin) {
        mixin.getProperties().forEach((mixinProperty) -> {
            Property property = this.properties.get(mixinProperty.getName());
            if (property == null) {
                this.addProperty(mixinProperty);
            } else {
                Map<String, String> meta = property.getMeta();
                mixinProperty.getMeta().forEach((metaName, metaValue) -> {
                    if (!meta.containsKey(metaName)) {
                        property.addMeta(metaName, metaValue);
                    }
                });
            }
        });
    }
}
