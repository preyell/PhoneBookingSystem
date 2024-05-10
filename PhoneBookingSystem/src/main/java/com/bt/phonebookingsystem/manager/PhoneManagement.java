package com.bt.phonebookingsystem.manager;

import java.util.List;

import com.bt.phonebookingsystem.exception.PhoneBookingException;
import com.bt.phonebookingsystem.exception.PhoneModelNotFoundException;
import com.bt.phonebookingsystem.model.Phone;

/**
 * This interface defines the contract for managing phones in the system.
 * Implementations of this interface provide functionality for retrieving,
 * booking, and returning phones.
 */
public interface PhoneManagement {

	/**
	 * Inject the list of Phone objects representing all the phones in the system.
	 * @param phones 
	 */
	void setPhones(List<Phone> phones);
	/**
     * Retrieves a list containing all the phones managed by the system.
     *
     * @return A list of Phone objects representing all the phones managed by the system.
     */
	List<Phone> getAllPhones();

	/**
     * Books a phone for the specified user.
     *
     * @param model The model of the phone to book.
     * @param user  The name of the user booking the phone.
     * @throws PhoneBookingException       if the phone cannot be booked due to an error.
     * @throws PhoneModelNotFoundException if the specified model is not found in the system.
     */
	void bookPhone(String model, String user) throws PhoneBookingException, PhoneModelNotFoundException;

	/**
     * Returns a phone that was previously booked.
     *
     * @param model The model of the phone to return.
     * @throws PhoneBookingException       if the phone cannot be returned due to an error.
     * @throws PhoneModelNotFoundException if the specified model is not found in the system.
     */
	void returnPhone(String model) throws PhoneBookingException, PhoneModelNotFoundException;

	/**
     * Retrieves a list of phones with the specified model.
     *
     * @param model The model of the phones to retrieve.
     * @return A list of phones with the specified model.
     * @throws PhoneModelNotFoundException if the specified model is not found in the system.
     */
	List<Phone> getPhones(String model) throws PhoneModelNotFoundException;

}
