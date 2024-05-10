package com.bt.phonebookingsystem.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for loading phone configurations from a properties file.
 */
public class PhoneLoader {
	
	private static final Logger logger = LogManager.getLogger(PhoneLoader.class);

	/**
	 * Loads phone configurations from the specified properties file.
	 *
	 * @param configFile The path to the properties file containing phone
	 *                   configurations.
	 * @return A Properties object containing the loaded phone configurations.
	 */
	public static Properties loadPhonesFromConfig(String configFile) {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Optional<InputStream> streamOptional = Optional.ofNullable(loader.getResourceAsStream(configFile));

        streamOptional.ifPresent(stream -> {
            try (InputStream inputStream = stream) {
                properties.load(inputStream);
            } catch (IOException e) {
                logger.error("Error loading properties file", e);
            }
        });

        return properties;
    }
}