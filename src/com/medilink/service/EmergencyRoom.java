package com.medilink.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.medilink.model.Patient;
import com.medilink.model.Severity;
import com.medilink.utils.Input;

public class EmergencyRoom {
	
	public EmergencyRoom() {
		
	}
	
	public void addPatient(Patient p, List<Patient> patientList) {
		patientList.add(p);
		reSort(patientList);
	}
	
	public void performTriage(List<Patient> patientList) {
		if (patientList.size() == 0) {
			System.out.println("There are no patients currently!");
			return;
		}
		
		int patientId = Input.getInt("Enter the id of the patient: ", true);
		Severity severity = Input.getSeverity("Enter the severity of the patient: ");
		
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getPatientId() == patientId) {
				patientList.get(i).setSeverity(severity);
				reSort(patientList); // When status changes, list has to be reSorted
				break;
			}
		}
	}
	
	public void reSort(List<Patient> patientList) {
		Collections.sort(patientList);
	}
	
	public Optional<Patient> getNextPatient(List<Patient> patientList) {
		if (patientList.isEmpty()) { 
			return Optional.empty(); // Returns empty instead of null
		}
		Patient p = patientList.remove(0); // '.remove()' does return the item as well as removing from the list.
		return Optional.of(p); // Patient is now 'wrapped' inside optional
	}
	
	public List<Patient> getQueueState(List<Patient> patientList){
		return new ArrayList<Patient>(patientList);
	}
}
 	

