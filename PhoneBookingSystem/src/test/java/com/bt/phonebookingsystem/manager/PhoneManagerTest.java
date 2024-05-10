package com.bt.phonebookingsystem.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bt.phonebookingsystem.exception.PhoneBookingException;
import com.bt.phonebookingsystem.exception.PhoneModelNotFoundException;
import com.bt.phonebookingsystem.model.Phone;

class PhoneManagerTest {

	private PhoneManagement phoneManager;
    private List<Phone> phones;

    @BeforeEach
    public void setUp() {
        // Create a new instance of PhoneManager
        phoneManager = PhoneManager.getInstance();
        
        // Create sample phones
        phones = new ArrayList<>();
        Phone phoneA = new Phone("ModelA");
        Phone phoneB = new Phone("ModelB");
        phones.add(phoneA);
        phones.add(phoneB);
        
        // Set the phones in the PhoneManager
        phoneManager.setPhones(phones);
    }


    @Test
    public void testGetPhones() throws PhoneModelNotFoundException {
      

        // Test getting phones by model
        List<Phone> result = phoneManager.getPhones("ModelA");
        assertEquals(1, result.size());
        assertEquals("ModelA", result.get(0).getModel());
    }

    @Test
    public void testBookPhone() throws PhoneBookingException, PhoneModelNotFoundException {
        // Test booking a phone
        phoneManager.bookPhone("ModelB", "User1");
        assertFalse(phones.get(1).isAvailable()); // Assuming phoneB is at index 1
        assertEquals("User1", phones.get(1).getBookedBy()); // Assuming phoneB is at index 1
    }

    @Test
    public void testReturnPhone() throws PhoneBookingException, PhoneModelNotFoundException {
        // Test returning a phone
        Phone phoneB = phones.get(1); // Assuming phoneB is at index 1
        phoneB.setAvailable(false);
        phoneB.setBookedBy("User1");
        phoneManager.returnPhone("ModelB");
        assertTrue(phoneB.isAvailable());
        assertNull(phoneB.getBookedBy());
    }
    
    @Test
    public void testGetPhonesModelNotFound() {
        // Test getting phones by a model that doesn't exist
        assertThrows(PhoneModelNotFoundException.class, () -> phoneManager.getPhones("InvalidModel"));
    }

    @Test
    public void testBookPhoneAlreadyBooked() throws PhoneBookingException, PhoneModelNotFoundException {
        // Set one of the phones as booked
        Phone bookedPhone = phones.get(0);
        bookedPhone.setAvailable(false);
        
        // Test booking a phone that is already booked
        assertThrows(PhoneBookingException.class, () -> phoneManager.bookPhone(bookedPhone.getModel(), "User1"));
    }
    
    @Test
    public void testReturnPhoneNotBooked() {
        // Test returning a phone that is not booked
        assertThrows(PhoneBookingException.class, () -> phoneManager.returnPhone("ModelA"));
    }
    
    @Test
    public void testGetAllPhones() {
        // Test getting all phones
        List<Phone> allPhones = phoneManager.getAllPhones();
        
        assertEquals(phones.size(), allPhones.size());
        assertTrue(allPhones.containsAll(phones));
    }

   
}
