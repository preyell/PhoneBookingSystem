package com.bt.phonebookingsystem.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class PhoneLoaderTest {

    @Test
    public void testLoadPhonesFromConfig() {
        // Load phones from the test properties file
        String configFile = "phones.properties";
        Properties properties = PhoneLoader.loadPhonesFromConfig(configFile);

        // Verify that properties are loaded correctly
        assertNotNull(properties);
        assertFalse(properties.isEmpty());
        assertEquals("Samsung Galaxy S9", properties.getProperty("samsung.s9"));
        assertEquals("Samsung Galaxy S8", properties.getProperty("samsung.s8"));
        assertEquals("Motorola Nexus 6", properties.getProperty("motorola.nexus6"));
        assertEquals("Oneplus 9", properties.getProperty("oneplus.9"));
        assertEquals("Apple iPhone 13", properties.getProperty("apple.iphone13"));

    }
    
    @Test
    public void testLoadPhonesFromConfigNegativeTest() {
        // Test loading phones from a non-existent config file
        String configFile = "nonexistent.properties";
        Properties properties = PhoneLoader.loadPhonesFromConfig(configFile);
        assertTrue(properties.isEmpty(), "Properties should be empty ");

        // Test loading phones from an empty config file
        configFile = "empty.properties";
        properties = PhoneLoader.loadPhonesFromConfig(configFile);
        assertNotNull(properties, "Properties should not be null");
        assertTrue(properties.isEmpty(), "Properties should be empty ");

    }
}