package com.hlws.enums;

public enum Side {
	WITHIN_STATE("Within State"), OUTSIDE_STATE("Outside State");
	
	String value;
	private Side(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
