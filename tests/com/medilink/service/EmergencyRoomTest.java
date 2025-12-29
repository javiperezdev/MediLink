package com.medilink.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.medilink.model.Patient;
import com.medilink.model.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class EmergencyRoomTest {
	
	private EmergencyRoom er;
	private Patient p1, p2, p3, p4, p5;
	
	@BeforeEach
	void setUp() {
		er = new EmergencyRoom();
		
		p1 = new Patient(1, "Maria Garcia");
	    p2 = new Patient(2, "Juan Perez");
	    p3 = new Patient(3, "Lucia Fernandez");
	    p4 = new Patient(4, "Carlos Ruiz");
        p5 = new Patient(5, "Ana Lopez");
	    
	    p1.setSeverity(Severity.MODERATE);  
        p2.setSeverity(Severity.CRITICAL);  
        p3.setSeverity(Severity.LOW);
	    p4.setSeverity(Severity.SERIOUS);
        p5.setSeverity(Severity.CRITICAL);
	    
	    er.addPatient(p1);
	    er.addPatient(p2);
	    er.addPatient(p3);
	    er.addPatient(p4);
	    er.addPatient(p5);
	    
	}
		
	@Test
	void testReSort() {
	    List<Patient> expected = new ArrayList<Patient>();
	    expected.add(p2);
	    expected.add(p5);
	    expected.add(p4);
	    expected.add(p1);
	    expected.add(p3);
	    
	    assertIterableEquals(expected, er.getQueueState(er.waitingQueue));
	   
	}
	
	@Test
	void testGetNextPatient() {
		Optional<Patient> box = er.getNextPatient(er.waitingQueue);
		assertTrue(box.isPresent(), "Queue should not be empty!");
		
		Patient p = box.get();
		Patient expected = p2;
		assertEquals(expected.getPatientId(), p.getPatientId());
	}
		
}
