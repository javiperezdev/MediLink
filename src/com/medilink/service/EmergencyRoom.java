package com.medilink.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.medilink.model.Patient;
import com.medilink.model.Severity;

public class EmergencyRoom {
	List<Patient> waitingQueue = new ArrayList<Patient>();
	
	public EmergencyRoom() {
		
	}
	
	public void addPatient(Patient p) {
		this.waitingQueue.add(p);
		reSort(this.waitingQueue);
	}
	
	public void performTriage(int patientId, Severity s) {
		if (waitingQueue.size() == 0) {
			System.out.println("There are no patients currently!");
			return;
		}
		
		for (int i = 0; i < waitingQueue.size(); i++) {
			if (waitingQueue.get(i).getPatientId() == patientId) {
				waitingQueue.get(i).setSeverity(s);
				reSort(this.waitingQueue); // When status changes, list has to be reSorted
				break;
			}
		}
	}
	
	public void reSort(List<Patient> waitingQueue) {
		Collections.sort(waitingQueue);
	}
	
	public Optional<Patient> getNextPatient(List<Patient> waitingQueue) {
		if (waitingQueue.isEmpty()) { 
			return Optional.empty(); // Returns empty instead of null
		}
		Patient p = waitingQueue.remove(0); // '.remove()' does return the item as well as removing from the list.
		return Optional.of(p); // Patient is now 'wrapped' inside optional
	}
	
	public List<Patient> getQueueState(List<Patient> waitingQueue){
		return new ArrayList<Patient>(this.waitingQueue);
	}
}
 	

