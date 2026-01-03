package com.medilink.utils;

import java.util.Scanner;

import com.medilink.model.Severity;

public class Input {
	private static Scanner sc = new Scanner(System.in);
	private static final String INT_ERROR = "\nERROR: you should enter a numeric value!";
	
	public static int getInt() {
		int number = -1;
		try {
			number = Integer.parseInt(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}

	public static int getInt(String text) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	public static int getInt(String text, boolean repeat) {
		int number = -1;
		boolean success = false;
		do {
			System.out.print(text);
			try {
				number = Integer.parseInt(sc.nextLine().trim());
				success = true;
			} catch (Exception e) {
				System.out.println(INT_ERROR);
				if(!repeat) {
					return -1;
				}
			}
		} while(!success);
		return number;
	}
	
	public static Severity getSeverity(String text) {
		boolean success = false;
		Severity severity = null;
		do {
			System.out.print(text);
			try {
				severity = Severity.valueOf(sc.nextLine().trim());
				success = true;
			} catch (Exception e) {
				System.out.println("ERROR: you should enter a value that is in ('LOW', 'MODERATE', 'SERIOUS', 'CRITICAL')!");
			}
		} while(!success);
		return severity;
	}


	public static String getString() {
		String value = sc.nextLine().trim();
		return value;
	}


	public static String getString(String text) {
		System.out.print(text);
		return sc.nextLine().trim();
	}
}

