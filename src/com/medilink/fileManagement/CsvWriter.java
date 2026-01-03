package com.medilink.fileManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.medilink.model.*;

public class CsvWriter {
	static String csvPath = "assets/patients.csv";
	
	public CsvWriter() {
		
	}
	
	public void toCsv(List<Patient> patientsList) {
		StringBuilder sb = new StringBuilder();
		try (FileWriter fw = new FileWriter(csvPath)) {
			for (Patient p : patientsList) {
				sb.append(p.getPatientId()).append(",").append(p.getName()).append(",").append(p.getSeverity()).append(",").append(p.getArrivalTime()).append("\n");
			}
			fw.write(sb.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
