package com.bt.phonebookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bt.phonebookingsystem.manager.PhoneManagement;
import com.bt.phonebookingsystem.manager.PhoneManager;
import com.bt.phonebookingsystem.model.Phone;
import com.bt.phonebookingsystem.ui.CommandLineInterface;
import com.bt.phonebookingsystem.utility.PhoneLoader;

/**
 * This class represents the main entry point for the phone booking system
 * application. It initializes and starts the command-line interface.
 */
public class BookingSystem {
	
	private static final Logger logger = LogManager.getLogger(BookingSystem.class);
	/**
	 * The main method of the application. It initializes the command-line interface
	 * and starts the application.
	 * 
	 * @param args The command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		PhoneManagement phoneManager = PhoneManager.getInstance(); 
		List<Phone> phones = loadPhones();
		phoneManager.setPhones(phones);
		CommandLineInterface cli = new CommandLineInterface(phoneManager);
		cli.start();
	}
	
	/**
	 * Loads the phones from a configuration file and adds them to the list of
	 * phones. The configuration file contains mappings of phone models to their
	 * names.
	 */
	private static List<Phone> loadPhones() {
		logger.info("Loading phones from config file");
		Optional<Properties> optionalProperties = Optional
				.ofNullable(PhoneLoader.loadPhonesFromConfig("phones.properties"));
		List<Phone> phones = new ArrayList<Phone>();
		if (optionalProperties.isPresent()) {
			Properties properties = optionalProperties.get();
			for (String key : properties.stringPropertyNames()) {
				String phoneName = properties.getProperty(key);
				phones.add(new Phone(phoneName));
			}
			logger.info("Loaded phones from config file");
		} else {
			logger.error("Failed to load phones from config file");
			// Handle the case where properties are not loaded
			System.out.println("Failed to load phones from config file");
		}
		return phones;
	}

}
