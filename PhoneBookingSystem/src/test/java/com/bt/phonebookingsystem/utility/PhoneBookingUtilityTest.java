package com.bt.phonebookingsystem.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class PhoneBookingUtilityTest {

    @Test
    void testFormatDateTime() {
        // Test formatting a non-null LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2024, 5, 10, 12, 30);
        String formattedDateTime = PhoneBookingUtility.formatDateTime(dateTime);
        assertEquals("10-05-2024 12:30PM", formattedDateTime);

        // Test formatting a null LocalDateTime
        String nullFormattedDateTime = PhoneBookingUtility.formatDateTime(null);
        assertNull(nullFormattedDateTime);
    }
}