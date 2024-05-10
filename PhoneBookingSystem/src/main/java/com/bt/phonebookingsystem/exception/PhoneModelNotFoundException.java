package com.bt.phonebookingsystem.exception;

/**
 * Exception thrown when a phone model is not found. This exception is typically
 * thrown when attempting to perform operations on a phone model that does not
 * exist in the system.
 */
public class PhoneModelNotFoundException extends Exception{
	
	/**
     * Constructs a new PhoneModelNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PhoneModelNotFoundException(String message) {
        super(message);
    }

}
