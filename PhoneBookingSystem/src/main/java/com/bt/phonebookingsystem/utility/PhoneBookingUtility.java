package com.bt.phonebookingsystem.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Utility class for formatting date and time.
 */
public class PhoneBookingUtility {

	/**
	 * Private constructor to prevent instantiation
	 */
	private PhoneBookingUtility() {

	}

	/**
     * Formats the given date and time.
     * 
     * @param dateTime the LocalDateTime object to format
     * @return a formatted string representing the date and time, or null if dateTime is null
     */
	public static String formatDateTime(final LocalDateTime dateTime) {

		return Optional.ofNullable(dateTime).map(dt -> LocalDateTime.parse(dt.toString()))
				.map(timestamp -> timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mma"))).orElse(null);
	}

}
