package com.medilink.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.medilink.model.Patient;
import com.medilink.model.Severity;

class EmergencyRoomTest {

    private EmergencyRoom er;
    private List<Patient> patientList;

    @BeforeEach
    void setUp() {
        er = new EmergencyRoom();
        patientList = new ArrayList<>();
    }

    @Test
    void testAddPatient_SortsBySeverity() {
        Patient lowP = new Patient(1, "Low Priority", LocalDateTime.now(), Severity.LOW);
        Patient criticalP = new Patient(2, "Critical Priority", LocalDateTime.now(), Severity.CRITICAL);
        Patient moderateP = new Patient(3, "Moderate Priority", LocalDateTime.now(), Severity.MODERATE);

        er.addPatient(lowP, patientList);
        er.addPatient(criticalP, patientList);
        er.addPatient(moderateP, patientList);

        assertEquals(Severity.CRITICAL, patientList.get(0).getSeverity(), "First patient should be CRITICAL");
        assertEquals(Severity.MODERATE, patientList.get(1).getSeverity(), "Second patient should be MODERATE");
        assertEquals(Severity.LOW, patientList.get(2).getSeverity(), "Third patient should be LOW");
    }

    @Test
    void testAddPatient_SortsByArrivalTimeWhenSeverityIsEqual() {
        LocalDateTime now = LocalDateTime.now();
        

        Patient earlyP = new Patient(1, "Early", now.minusHours(2), Severity.SERIOUS);
        Patient lateP = new Patient(2, "Late", now.minusHours(1), Severity.SERIOUS);

        er.addPatient(lateP, patientList);
        er.addPatient(earlyP, patientList);

        assertEquals("Early", patientList.get(0).getName(), "Patient who arrived earlier should be first");
        assertEquals("Late", patientList.get(1).getName());
    }

    @Test
    void testGetNextPatient_ReturnsAndRemovesPatient() {
        Patient p1 = new Patient(1, "P1", LocalDateTime.now(), Severity.SERIOUS);
        er.addPatient(p1, patientList);


        Optional<Patient> result = er.getNextPatient(patientList);


        assertTrue(result.isPresent(), "Should return a patient");
        assertEquals("P1", result.get().getName());
        assertTrue(patientList.isEmpty(), "List should be empty after retrieving the patient");
    }

    @Test
    void testGetNextPatient_ReturnsEmptyWhenListIsEmpty() {

        Optional<Patient> result = er.getNextPatient(patientList);


        assertTrue(result.isEmpty(), "Should return empty Optional when list is empty");
    }

    @Test
    void testReSort_UpdatesOrderAfterSeverityChange() {

        Patient p1 = new Patient(1, "P1", LocalDateTime.now(), Severity.LOW);
        Patient p2 = new Patient(2, "P2", LocalDateTime.now(), Severity.MODERATE);
        
        er.addPatient(p1, patientList);
        er.addPatient(p2, patientList);
        

        assertEquals("P2", patientList.get(0).getName());

        p1.setSeverity(Severity.CRITICAL);
        er.reSort(patientList);


        assertEquals("P1", patientList.get(0).getName(), "P1 should now be first after severity upgrade");
    }

    @Test
    void testGetQueueState_ReturnsCopy() {
        Patient p1 = new Patient(1, "P1", LocalDateTime.now(), Severity.LOW);
        er.addPatient(p1, patientList);

        List<Patient> snapshot = er.getQueueState(patientList);
        snapshot.clear(); 

        assertTrue(snapshot.isEmpty());
        assertFalse(patientList.isEmpty(), "Original list should not be affected by modifying the snapshot");
    }
}