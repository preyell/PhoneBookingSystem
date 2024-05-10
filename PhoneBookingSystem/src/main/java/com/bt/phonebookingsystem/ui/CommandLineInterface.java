package com.bt.phonebookingsystem.ui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bt.phonebookingsystem.exception.PhoneBookingException;
import com.bt.phonebookingsystem.exception.PhoneModelNotFoundException;
import com.bt.phonebookingsystem.manager.PhoneManagement;
import com.bt.phonebookingsystem.manager.PhoneManager;
import com.bt.phonebookingsystem.model.Phone;

/**
 * This class represents the command-line interface for the phone booking
 * system. It provides functionality for booking, returning, and displaying
 * phone details.
 */
public class CommandLineInterface {

	private static final Logger logger = LogManager.getLogger(CommandLineInterface.class);
	private Scanner scanner;
	private PhoneManagement phoneManager;

	/**
	 * Constructs a new CommandLineInterface object. Initializes the scanner and
	 * obtains an instance of the PhoneManager.
	 */
	public CommandLineInterface(PhoneManagement phoneMgmt) {
		scanner = new Scanner(System.in);
		this.phoneManager = phoneMgmt;
	}

	/**
	 * Starts the phone booking system command-line interface. Displays the
	 * available options and prompts the user for input.
	 */
	public void start() {
		System.out.println("Welcome to phone booking system. Please find the list of phones below - \n");

		PhonePrinter.printAllPhoneModels(PhoneManager.getInstance().getAllPhones());

		while (true) {
			System.out.println("\n1. Enter 1 to Book a phone");
			System.out.println("2. Enter 2 to Return a phone");
			System.out.println("3. Enter 3 to Enter model name to check its availability and details");
			System.out.println("4. Enter 4 to Check availability and details of all the phones in the system");
			System.out.println("5. Enter 5 to Exit");
			System.out.print("Please enter your choice: ");
			String choice = scanner.nextLine();
			logger.info("Choice entered: " + choice);
			switch (choice) {
			case "1" -> bookPhone();
			case "2" -> returnPhone();
			case "3" -> displayPhone();
			case "4" -> displayAllPhones();
			case "5" -> {
				System.out.println("Thank you!");
				return;
			}
			default -> System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	/**
	 * Prompts the user to enter the user name and model of the phone to book, then
	 * calls the PhoneManager to book the phone.
	 */
	private void bookPhone() {
		logger.info("Book Phone option entered");
		System.out.print("Enter the user name ");

		String userName = scanner.nextLine();
		logger.info("Book phone for  user name: " + userName);

		System.out.print("Select the model of the phone to book: ");
		String phoneModel = scanner.nextLine().trim();
		logger.info("Model selected for booking: " + phoneModel);

		try {
			phoneManager.bookPhone(phoneModel, userName);
			logger.info("Phone " + phoneModel + " booked successfully for user: " + userName);
		} catch (PhoneBookingException | PhoneModelNotFoundException e) {
			logger.error("Error while booking phone: " + phoneModel + e);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Prompts the user to enter the model of the phone to return, then calls the
	 * PhoneManager to return the phone.
	 */
	private void returnPhone() {
		logger.info("Return Phone option entered");
		System.out.print("Select the model of the phone to return: ");
		String phoneModel = scanner.nextLine().trim();
		logger.info("Model selected for returning : " + phoneModel);
		try {
			phoneManager.returnPhone(phoneModel);
			logger.info("Phone " + phoneModel + " returned successfully");
		} catch (PhoneBookingException | PhoneModelNotFoundException e) {
			logger.error("Error while returning the phone: " + phoneModel + e);
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Prompts the user to enter the model of the phone to display details, then
	 * calls the PhoneManager to get the phone details and prints them. If there are
	 * multiple sets of the same model, all the sets are displayed
	 */
	private void displayPhone() {
		logger.info("Display Phone option entered");
		System.out.print("Enter the model of the phone to check the details: ");
		String model = scanner.nextLine().trim();
		logger.info("Model selected for display " + model);
		List<Phone> phones;
		try {
			phones = phoneManager.getPhones(model);
			PhonePrinter.printPhones(phones);
			logger.info("phone: " + model + " displayed successfully");
		} catch (PhoneModelNotFoundException e) {
			logger.error("Error while displaying the phone: " + model + e);
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Calls the PhoneManager to get all the phones in the system and prints their
	 * details.
	 */
	private void displayAllPhones() {
		logger.info("Display all the Phones option entered");
		Optional<List<Phone>> optionalPhones = Optional.ofNullable(phoneManager.getAllPhones());

		optionalPhones.ifPresentOrElse(phones -> {
			logger.info("Displaying the phones in the system");
			PhonePrinter.printPhones(phones);
			logger.info("Displayed all the phones in the system");
		}, () -> {
			logger.info("No phones were found in the system.");
			System.out.println("No phones found!");
		});

	}

}