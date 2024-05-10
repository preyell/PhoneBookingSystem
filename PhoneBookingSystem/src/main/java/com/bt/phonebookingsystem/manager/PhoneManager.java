package com.bt.phonebookingsystem.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bt.phonebookingsystem.exception.PhoneBookingException;
import com.bt.phonebookingsystem.exception.PhoneModelNotFoundException;
import com.bt.phonebookingsystem.model.Phone;
import com.bt.phonebookingsystem.utility.PhoneBookingUtility;
import com.bt.phonebookingsystem.utility.PhoneLoader;

/**
 * Manages the phones in the system including loading, booking, and returning
 * phones.
 */
public class PhoneManager implements PhoneManagement {

	private static final Logger logger = LogManager.getLogger(PhoneManager.class);

	/**
	 * Holder class for lazy initialization of the singleton instance.
	 */
	private static class SingletonHolder {
		private static final PhoneManagement INSTANCE = new PhoneManager();
	}

	private List<Phone> phones;

	/**
	 * Private constructor to enforce singleton pattern.
	 */
	private PhoneManager() {
	}

	/**
	 * Returns the singleton instance of the PhoneManager.
	 * 
	 * @return The singleton instance of the PhoneManager.
	 */
	public static PhoneManagement getInstance() {
		return SingletonHolder.INSTANCE;
	}


	/**
	 * Inject the list of Phone objects representing all the phones in the system.
	 * @param phones 
	 */
	@Override
	public void setPhones(List<Phone> phones) {
		
		this.phones = phones;
		
	}
	

	/**
	 * Gets a list of phones with the specified model.
	 * 
	 * @param model The model of the phones to retrieve.
	 * @return A list of phones with the specified model.
	 * @throws PhoneModelNotFoundException thrown when the specified model is not
	 *                                     found in the system.
	 */
	public List<Phone> getPhones(String model) throws PhoneModelNotFoundException {
		logger.info("Finding the phone " + model);
		List<Phone> filteredPhones = phones.stream().filter(phone -> phone.getModel().equals(model))
				.collect(Collectors.toList());

		if (filteredPhones.isEmpty()) {
			logger.error("Phone model '" + model + "' not found in the system");
			throw new PhoneModelNotFoundException("Error: Phone model '" + model + "' not found!");
		}
		logger.info("Found the phone " + model);
		return filteredPhones;
	}

	/**
	 * Retrieves a list containing all the phones managed by this PhoneManager.
	 *
	 * @return A list of Phone objects representing all the phones managed by this
	 *         PhoneManager.
	 */
	public List<Phone> getAllPhones() {
		return phones;
	}

	/**
	 * Books a phone for the specified user.
	 *
	 * @param model The model of the phone to book.
	 * @param user  The name of the user booking the phone.
	 * @throws PhoneModelNotFoundException thrown when the model is not found in the system
	 * @throws PhoneBookingException thrown when there is some error while booking the phone
	 */
	public void bookPhone(String model, String user) throws PhoneBookingException, PhoneModelNotFoundException {
		Optional<Phone> phone;
		logger.info("Booking the phone " + model + " for user " + user);
		phone = getAvailablePhone(model);
		if (phone.isPresent()) {
			Phone requestedPhone = phone.get();
			requestedPhone.setAvailable(false);
			requestedPhone.setBookedBy(user);
			requestedPhone.setBookedAt(PhoneBookingUtility.formatDateTime(LocalDateTime.now()));
			System.out.println("Success: Phone " + model + " booked.");
		} else {
			logger.error("Phone " + model + " is not avaialable to book. It is already booked!");
			throw new PhoneBookingException("Error: Phone " + model + " is already booked!");

		}
	}

	/**
	 * Returns a phone that was previously booked.
	 *
	 * @param model The model of the phone to return.
	 * @throws PhoneModelNotFoundException  thrown when the model is not found in the system
	 * @throws PhoneBookingException thrown when there is some error while returning the phone
	 */
	public void returnPhone(String model) throws PhoneBookingException, PhoneModelNotFoundException {
		Optional<Phone> phone;
		phone = getBookedPhone(model);
		logger.info("Returning the phone " + model);
		if (phone.isPresent()) {
			Phone bookedPhone = phone.get();
			bookedPhone.setAvailable(true);
			bookedPhone.setBookedBy(null);
			bookedPhone.setBookedAt(null);
			System.out.println("Success: Phone " + model + " returned.");
		} else {
			logger.error("Phone " + model + " is not booked yet");
			throw new PhoneBookingException("Error: Phone " + model + " is not booked yet!");
		}

	}

	/**
	 * Retrieves an available phone of the specified model.
	 *
	 * @param model The model of the phone to retrieve.
	 * @return An Optional containing an available Phone object with the specified
	 *         model, or an empty Optional if no such phone is available.
	 * @throws PhoneModelNotFoundException  thrown when the model is not found in the system
	 */
	private Optional<Phone> getAvailablePhone(String model) throws PhoneModelNotFoundException {
		return getPhones(model).stream().filter(Phone::isAvailable).findFirst();
	}

	/**
	 * Retrieves a booked phone of the specified model.
	 *
	 * @param model The model of the phone to retrieve.
	 * @return An Optional containing a booked Phone object with the specified
	 *         model, or an empty Optional if no such phone is booked.
	 * @throws PhoneModelNotFoundException thrown when the model is not found in the system
	 */
	private Optional<Phone> getBookedPhone(String model) throws PhoneModelNotFoundException {
		return getPhones(model).stream().filter(phone -> !phone.isAvailable()).findFirst();
	}


}