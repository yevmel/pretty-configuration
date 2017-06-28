package yevgeniy.melnichuk.prettyconfiguration.model.configuration;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {
    public static final String PROPERTY_NAME = "property-name";
    public static final String MIXIN_PROPERTY_VALUE = "mixin-value";
    public static final String MIXIN_META_NAME = "mixin-meta-name";
    public static final String MIXIN_META_VALUE = "mixin-meta-value";
    public static final String EXISTING_PROPERTY_VALUE = "existing-property-value";


    @Test
    public void shouldCopyNewPropertiesFromMixin() {
        Configuration configuration = new Configuration("test-configuration");
        configuration.applyMixin(setupMixin());

        assertEquals(1, configuration.getProperties().size());

        Property property = configuration.getProperty(PROPERTY_NAME);
        assertNotNull(property);
        assertEquals(MIXIN_PROPERTY_VALUE, property.getValue());
    }

    @Test
    public void shouldMergeMetaInformationForExistingProperty() {
        Property existingProperty = new Property(PROPERTY_NAME);
        existingProperty.setValue(EXISTING_PROPERTY_VALUE);

        Configuration configuration = new Configuration("test-configuration");
        configuration.addProperty(existingProperty);
        configuration.applyMixin(setupMixin());

        assertEquals(1, configuration.getProperties().size());

        Property property = configuration.getProperty(PROPERTY_NAME);
        assertNotNull(property);
        assertEquals(EXISTING_PROPERTY_VALUE, property.getValue());

        String metaValue = property.getMeta().get(MIXIN_META_NAME);
        assertEquals(MIXIN_META_VALUE, metaValue);
    }

    private Configuration setupMixin() {
        Property mixinProperty = new Property(PROPERTY_NAME);
        mixinProperty.setValue(MIXIN_PROPERTY_VALUE);
        mixinProperty.addMeta(MIXIN_META_NAME, MIXIN_META_VALUE);

        Configuration mixin = new Configuration("test-mixin");
        mixin.addProperty(mixinProperty);

        return mixin;
    }
}