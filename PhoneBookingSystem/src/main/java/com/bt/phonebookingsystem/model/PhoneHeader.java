package com.bt.phonebookingsystem.model;

/**
 * Enum representing headers for phone details.
 */
public enum PhoneHeader {

	MODEL("Model"), AVAILABLE("Available"), BOOKED_BY("Booked By"), BOOKED_AT("Booked At");

	private final String columnName;

	/**
	 * Constructs a PhoneHeader with the specified column name.
	 * 
	 * @param columnName the column name
	 */
	PhoneHeader(String columnName) {
		this.columnName = columnName;
	}

	/**
     * Gets the column name associated with the PhoneHeader.
     * 
     * @return the column name
     */
	public String getColumnName() {
		return columnName;
	}
}
