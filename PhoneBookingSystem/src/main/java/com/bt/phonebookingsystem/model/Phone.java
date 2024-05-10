package com.bt.phonebookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a mobile phone with information about its model, availability,
 * booking status, and booking timestamp.
 */
@Getter
@Setter
@ToString

public class Phone {
	/**
	 * The model of the phone.
	 */
	private String model;
	/**
	 * Indicates whether the phone is available for booking.
	 */
	private boolean available;
	/**
	 * The name of the user who booked the phone.
	 */
	private String bookedBy;
	/**
	 * The timestamp when the phone was booked.
	 */
	private String bookedAt;

	/**
	 * Constructs a phone with the specified model.
	 *
	 * @param model The model of the phone.
	 */
	public Phone(String model) {
		this.model = model;
		this.available = true;
	}

}
