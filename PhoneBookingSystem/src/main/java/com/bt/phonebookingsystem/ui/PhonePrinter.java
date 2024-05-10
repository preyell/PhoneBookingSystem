package com.bt.phonebookingsystem.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.bt.phonebookingsystem.model.Phone;
import com.bt.phonebookingsystem.model.PhoneHeader;

import dnl.utils.text.table.TextTable;

/**
 * Utility class for printing phone details.
 */
public class PhonePrinter {

	/**
	 * Private constructor to prevent instantiation
	 */
	private PhonePrinter() {

	}

	/**
	 * Prints all phone models. This method is used when the application starts and
	 * displays all the models available in the system.
	 */
	public static void printAllPhoneModels(List<Phone> allPhones) {
		Optional<List<Phone>> optionalPhones = Optional.ofNullable(allPhones);

		Object[][] data = optionalPhones
				.map(phones -> phones.stream().map(phone -> new Object[] { phone.getModel() }).toArray(Object[][]::new))
				.orElse(new Object[0][0]);

		String[] columnNames = Arrays.stream(PhoneHeader.values()).filter(header -> header == PhoneHeader.MODEL)
				.map(PhoneHeader::getColumnName).toArray(String[]::new);

		printTable(columnNames, data);
	}

	/**
	 * Prints phone details. This method displays all the phone models along with its details
	 * 
	 * @param phones the list of phones
	 */
	public static void printPhones(List<Phone> phones) {
		Object[][] data = phones.stream().map(phone -> new Object[] { phone.getModel(),
				phone.isAvailable() ? "Yes" : "No", phone.getBookedBy(), phone.getBookedAt() })
				.toArray(Object[][]::new);
		printTable(getHeaderNames(), data);
	}

	private static String[] getHeaderNames() {
		return Arrays.stream(PhoneHeader.values()).map(PhoneHeader::getColumnName).toArray(String[]::new);
	}

	private static void printTable(String[] columnNames, Object[][] data) {
		TextTable tt = new TextTable(columnNames, data);
		tt.setAddRowNumbering(true); // Add row numbering
		tt.setSort(0); // Sort by the first column
		tt.printTable();
	}
}
