package com.medilink.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.medilink.model.Patient;
import com.medilink.model.Severity;

import java.util.ArrayList;

class EmergencyRoomTest {
	
	private EmergencyRoom er;
	private Patient p1, p2, p3, p4, p5;
	
	@BeforeEach
	void setUp() {
		er = new EmergencyRoom();
		
		p1 = new Patient(001, "Maria Garcia");
	    p2 = new Patient(002, "Juan Perez");
	    p3 = new Patient(003, "Lucia Fernandez");
	    p4 = new Patient(004, "Carlos Ruiz");
        p5 = new Patient(005, "Ana Lopez");
	    
	    p1.setS(Severity.MODERATE);  
        p2.setS(Severity.CRITICAL);  
        p3.setS(Severity.LOW);
	    p4.setS(Severity.SERIOUS);
        p5.setS(Severity.CRITICAL);
	    
	    er.addPatient(p1);
	    er.addPatient(p2);
	    er.addPatient(p3);
	    er.addPatient(p4);
	    er.addPatient(p5);
	    
	}
		
	@Test
	void testReSort() {
	    ArrayList<Patient> expected = new ArrayList<Patient>();
	    expected.add(p2);
	    expected.add(p5);
	    expected.add(p4);
	    expected.add(p1);
	    expected.add(p3);
	    
	    assertIterableEquals(expected, er.getQueueState(er.waitingQueue));
	   
	}
	
	@Test
	void testGetNextPatientTest() {
		Patient p = er.getNextPatient(er.waitingQueue);
		Patient expected = p2;
		assertEquals(expected.getPatientId(), p.getPatientId());
	}
		
}
