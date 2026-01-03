package com.medilink.fileManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.medilink.model.Patient;
import com.medilink.model.Severity;
import com.medilink.service.EmergencyRoom;


public class CsvReader {
	static EmergencyRoom er = new EmergencyRoom(); 
	
	public CsvReader() {
		
	}
	
	public List<Patient> getAllPatients(String filePath) {
		List<Patient> patientsList = new ArrayList<Patient>();
		File f = new File(filePath);
		
		try(Scanner sc = new Scanner(f)) {	
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				try {
					String[] columns = data.split(",");
				
					if (columns.length != 4) {
						continue;
					}
					
					int id = Integer.parseInt(columns[0].trim());
					LocalDateTime arrivalTime = LocalDateTime.parse(columns[3].trim());
					Severity severity = Severity.valueOf(columns[2].trim());
					
					Patient p = new Patient(id, columns[1].trim(), arrivalTime, severity);
					
					patientsList.add(p);
				}	
				catch(Exception e) {
					System.err.println("Error occurred while parsing line '" + data + "' due to " + e.getMessage());
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException("Error: file not found in: " + f.getAbsolutePath(), e);
		}
		
		return patientsList;
	}
}