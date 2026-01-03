package com.medilink.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import com.medilink.fileManagement.*;
import com.medilink.model.Patient;
import com.medilink.model.Severity;
import com.medilink.service.EmergencyRoom;
import com.medilink.utils.DesktopLauncher;
import com.medilink.utils.Input;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	static EmergencyRoom er = new EmergencyRoom();
	static HtmlManager hm = new HtmlManager();
	static CsvReader reader = new CsvReader();
	static CsvWriter writer = new CsvWriter();
	
	static String htmlPath = "assets/patients.html";
	static String csvPath = "assets/patients.csv";
	
	public static void main(String[] args) {
	    List<Patient> loadedPatients = new ArrayList<Patient>();
	    loadedPatients = reader.getAllPatients(csvPath);
	    er.reSort(loadedPatients); 
	    
		boolean repeat = true;
		while (repeat) {
			
			switch (enteredOption()) {
				case "1":
					openTable(hm, htmlPath, loadedPatients);
					break;
				case "2":
					nextPatient(loadedPatients);
					break;
				case "3":
					newPatient(loadedPatients);
					break;
				case "4":
					changeSeverity(loadedPatients);
					break;
				case "0":
					writer.toCsv(loadedPatients);
					System.out.println("Goodbye!");
					repeat = false;
					break;
				default:
					System.out.println("Option entered wasn't valid!");
			}
		}    
	}
	
	public static String enteredOption() {
		return Input.getString("\n1 - Open Patients table\n2 - Get next patient\n3 - Add patient\n4 - Change patient severity\n0 - Save and exit\nEnter an option: ");
	}
	
	public static void openTable(HtmlManager hm, String htmlPath, List<Patient> loadedPatients) {
		hm.createHtml(hm.toHtml(loadedPatients), htmlPath);
		DesktopLauncher.openFile(htmlPath);
	}
	
	public static void newPatient(List<Patient> loadedPatients) {
		int patientId = Input.getInt("Enter the id of the patient: ", true);
		String name = Input.getString("Enter patient name: ");
		Severity severity = Input.getSeverity("Enter the severity of the patient: ");
		LocalDateTime arrivalTime = LocalDateTime.now();
	
		Patient p = new Patient(patientId, name, arrivalTime, severity);
		
		er.addPatient(p, loadedPatients);
		System.out.println("Patient added succesfully!\n");
	}

	public static void changeSeverity(List<Patient> loadedPatients) {
		er.performTriage(loadedPatients);	
	}
	
	public static void nextPatient(List<Patient> loadedPatients) {
		Optional<Patient> optPatient = er.getNextPatient(loadedPatients);
		
		if (optPatient.isEmpty()) {
			System.out.println("\nThere are no patients in the list!");
			return;
		}
		Patient p = optPatient.get();
		System.out.println("\nId: " + p.getPatientId() + "\nName: " + p.getName() + "\n");
	}
}
	


