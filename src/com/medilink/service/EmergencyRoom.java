package com.medilink.service;

import java.util.ArrayList;

import com.medilink.model.Patient;
import com.medilink.model.Severity;

public class EmergencyRoom {
	private ArrayList <Patient> waitingQueue = new ArrayList<Patient>();
	
	public EmergencyRoom() {
		
	}
	
	public void addPatient(Patient p) {
		waitingQueue.add(p);
	}
	
	public void performTriage(int patientId, Severity s) {
		if (waitingQueue.size() == 0) {
			System.out.println("There are no patients currently!");
			return;
		}
		for (int i = 0; i < waitingQueue.size(); i++) {
			if (waitingQueue.get(i).getPatientId() == patientId) {
				waitingQueue.get(i).setS(s);
			};
		}
	}
	
	// Next step is creating the helper method to resort when a patiente is added
	
	
}
