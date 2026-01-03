package com.medilink.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class DesktopLauncher {
	public static void openFile(String filePath) {
		if (!Desktop.isDesktopSupported()) return;

	    File file = new File(filePath);
	        
        try {
        	if (file.exists()) {
        		Desktop.getDesktop().browse(file.toURI());
	        }
	    } 
        catch (IOException e) {
        	System.err.println("Could not open file: " + e.getMessage());
	    }
	}
}
