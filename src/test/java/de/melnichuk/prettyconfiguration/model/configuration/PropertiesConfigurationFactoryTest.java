package de.melnichuk.prettyconfiguration.model.configuration;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesConfigurationFactoryTest {

    @Test
    public void shouldUseSuppliedIdentifier() {
        Properties properties = new Properties();
        Configuration configuration = new PropertiesConfigurationFactory().createConfigurationFromProperties(properties, "foo");
        assertEquals("foo", configuration.getIdentifier());
    }
}