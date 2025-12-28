package com.medilink.model;

public enum Severity {
	LOW(1),
	MODERATE(2),
	SERIOUS(3),
	CRITICAL(4);
	
	private final int level;
	
	private Severity(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
}
