package com.medilink.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


class PatientTest {

    @Test
    void testCompareTo_DifferentSeverity() {
        Patient critical = new Patient(1, "A", LocalDateTime.now(), Severity.CRITICAL);
        Patient low = new Patient(2, "B", LocalDateTime.now(), Severity.LOW);

        assertTrue(critical.compareTo(low) < 0, "Critical patient should come before Low severity");
        assertTrue(low.compareTo(critical) > 0, "Low severity should come after Critical");
    }

    @Test
    void testCompareTo_SameSeverity_DifferentTime() {
        LocalDateTime now = LocalDateTime.now();
        Patient older = new Patient(1, "Old", now.minusHours(1), Severity.MODERATE);
        Patient newer = new Patient(2, "New", now, Severity.MODERATE);
        
        assertTrue(older.compareTo(newer) < 0, "Patient waiting longer should come first");
    }
}