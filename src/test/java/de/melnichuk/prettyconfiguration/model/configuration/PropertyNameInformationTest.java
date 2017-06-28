package de.melnichuk.prettyconfiguration.model.configuration;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyNameInformationTest {

    @Test
    public void shouldExtractName() {
        PropertyNameInformation info = PropertyNameInformation.extractFromPropertyName("foo.bar");
        assertEquals("foo.bar", info.getName());
        assertNull(info.getMeta());
    }

    @Test
    public void shouldExtractNameAndMeta() {
        PropertyNameInformation info = PropertyNameInformation.extractFromPropertyName("foo.bar._baz");
        assertEquals("foo.bar", info.getName());
        assertEquals("baz", info.getMeta());
    }
}