package com.medilink.service;

import java.util.ArrayList;

import com.medilink.model.Patient;
import com.medilink.model.Severity;

public class EmergencyRoom {
	ArrayList <Patient> waitingQueue = new ArrayList<Patient>();
	
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
				waitingQueue.get(i).setS(s);
				break;
			}
		}
	}
	
	public ArrayList<Patient> reSort(ArrayList<Patient> waitingQueue) {
		boolean repeat = true;
		while (repeat) {
			repeat = false;
			for (int j = 0; j < waitingQueue.size() - 1; j++) {
				if (waitingQueue.get(j).compareTo(waitingQueue.get(j+1)) > 0) {
					Patient aux = waitingQueue.get(j+1);
					waitingQueue.set(j+1, waitingQueue.get(j));
					waitingQueue.set(j, aux);
					repeat = true;
				}
			}
		}
		return waitingQueue;
	}
	
	public Patient getNextPatient(ArrayList<Patient> waitingQueue) {
		if (waitingQueue.size() == 0) {
			System.out.println("There are no patients currently!");
			return null;
		}
		Patient p = waitingQueue.get(0);
		waitingQueue.remove(0);
		return p;
	}
	
	public ArrayList<Patient> getQueueState(ArrayList<Patient> waitingQueue){
		return this.waitingQueue;
	}
}
 	

