package com.bt.phonebookingsystem.exception;

/**
 * Exception thrown for phone booking/returning errors.
 */
public class PhoneBookingException extends Exception {
	/**
	 * Constructs a new PhoneBookingException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the
	 *                getMessage() method)
	 */
	public PhoneBookingException(String message) {
		super(message);
	}
}