package com.medilink.model;

import java.time.LocalDateTime;

public class Patient implements Comparable<Patient> { // Declaration of the promise
	private int patientId;
	private String name;
	private LocalDateTime arrivalTime = LocalDateTime.now();
	private Severity severity;
	
	@Override 
	public int compareTo(Patient other) {
		/* if severity level of 'other' is bigger than 'this', it returns 1, 
		 * when it is smaller it returns -1
		 * and when they have the same level, the tie breaker is the 'arrivalTime'
		 */ 
		int severityResult = (Integer.compare(other.severity.getLevel(), this.severity.getLevel()));
		
		if (severityResult != 0) {
			return severityResult;
		}
		
		return this.arrivalTime.compareTo(other.arrivalTime); // LocalDateTime has it's own compareTo
	}

	
	public Patient(int patientId, String name) {
		this.patientId = patientId;
		this.name = name;
		this.severity = null;
	}
	
	public Patient(int patientId, String name, LocalDateTime arrivalTime, Severity severity) {
		this.patientId = patientId;
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.setSeverity(severity);
	}



	public int getPatientId() {
		return patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		if (severity == null) {
			throw new IllegalArgumentException("Severity can not be null!");
		}
		this.severity = severity;
	}
	@Override
	public String toString() {
		return "patientId =" + patientId + "\nname =" + name + "\narrivalTime =" + arrivalTime + "\nseverity_level =" + severity;
	}
}
