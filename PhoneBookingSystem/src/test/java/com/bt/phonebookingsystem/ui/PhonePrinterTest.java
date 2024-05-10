package com.bt.phonebookingsystem.ui;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bt.phonebookingsystem.model.Phone;

public class PhonePrinterTest {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testPrintAllPhoneModels() {
    	// Create a list of phones
        List<Phone> phones = Arrays.asList(
                new Phone("Model1"),
                new Phone("Model2")
        );

    
        ConsoleOutputCapturer capturer = new ConsoleOutputCapturer();
        capturer.captureOutput(() -> {
            PhonePrinter.printAllPhoneModels(phones);
        }, (capturedOutput) -> {
            assertTrue(capturedOutput.contains("Model1"));
            assertTrue(capturedOutput.contains("Model2"));
            assertFalse(capturedOutput.contains("Model3"));
        });
    }

    
    @Test
    public void testPrintAllPhoneModelsWhenPhonesNull() {
    	// Create a list of phones
        List<Phone> phones = null;

    
        ConsoleOutputCapturer capturer = new ConsoleOutputCapturer();
        capturer.captureOutput(() -> {
            PhonePrinter.printAllPhoneModels(phones);
        }, (capturedOutput) -> {
            assertTrue(capturedOutput.contains("Model"));
        });
    }

    @Test
    public void testPrintPhones() {
    	// Create a list of phones
        List<Phone> phones = Arrays.asList(
                new Phone("Model1"),
                new Phone("Model2")
        );
        
        ConsoleOutputCapturer capturer = new ConsoleOutputCapturer();
        capturer.captureOutput(() -> {
            PhonePrinter.printPhones(phones);
        }, (capturedOutput) -> {
            assertTrue(capturedOutput.contains("Model1"));
            assertTrue(capturedOutput.contains("Model2"));
            assertFalse(capturedOutput.contains("Model3"));
        });
      
    }
}