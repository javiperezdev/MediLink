package com.medilink.fileManagement;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.medilink.model.Patient;

public class HtmlManager {
	public String toHtml(List<Patient> patientsList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"></head><body><h1>Patients</h1><table class='table table-dark'><tr><th>Patient Id</th><th>Name</th><th>Arrival Time</th><th>Severity</th></tr>");
		for (Patient p : patientsList) {
			sb.append("<tr><td>").append(p.getPatientId()).append("</td>");
			sb.append("<td>").append(p.getName()).append("</td>");
			sb.append("<td>").append(p.getArrivalTime()).append("</td>");
			sb.append("<td>").append(p.getSeverity()).append("</td></tr>");	
		}
		sb.append("</table></body></html>");
		return sb.toString();
	}
	
	public void createHtml(String data, String filePath) {	
		try (FileWriter fw = new FileWriter(filePath)) {
			fw.write(data);
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found at '" + filePath + "!");
		}
		catch (IOException e) {
			System.err.println("Error occurred: " + e.getMessage());
		}
	}
}
