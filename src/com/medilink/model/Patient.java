package com.medilink.model;

import java.time.LocalDateTime;

public class Patient implements Comparable<Patient> { // Declaration of the promise
	private int patientId;
	private String name;
	private LocalDateTime arrivalTime = LocalDateTime.now();
	private Severity s;
	
	@Override 
	public int compareTo(Patient other) {
		/* if severity level of 'other' is bigger than 'this', it returns 1, 
		 * when it is smaller it returns -1
		 * and when they have the same level, the tie breaker is the 'arrivalTime'
		 */ 
		int severityResult = (Integer.compare(other.s.getLevel(), this.s.getLevel()));
		
		if (severityResult != 0) {
			return severityResult;
		}
		
		return this.arrivalTime.compareTo(other.arrivalTime); // LocalDateTime has it's own compareTo
	}

	
	public Patient(int patientId, String name) {
		this.patientId = patientId;
		this.name = name;
		this.s = null;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patient_id) {
		this.patientId = patient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getEntryDate() {
		return arrivalTime;
	}

	public void setEntryDate(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Severity getS() {
		return s;
	}

	public void setS(Severity s) {
		if (s != null) {
			this.s = s;
		}
		else {
			System.out.println("Severity can't be null!");
		}
	}
	
	@Override
	public String toString() {
		return "patientId=" + patientId + "\nname=" + name + "\narrivalTime=" + arrivalTime + "\nseverity_level=" + s;
	}
}
