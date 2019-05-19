package com.hlws.enums;

public enum PANDataColumn {
	PAN("PAN", 6000),
	PAN_HOLDER("PAN HOLDER", 6000),
	TDS("TDS", 4000),
	CITY("CITY", 5000),
	STATE("STATE", 4000),
	ACCOUNT_NUMBER("ACCOUNT NUMBER", 5000),
	ACCOUNT_HOLDER("ACCOUNT HOLDER", 5000),
	BANK("BANK", 6000),
	BRANCH("BRANCH", 5000),
	IFSC("IFSC", 5000),
	VEHICLE_NUMBER("VEHICLE NUMBER", 5000),
	COMMENTS("COMMENTS", 4000);
	
	PANDataColumn(String val, Integer width){
		this.value = val;
		this.width = width;
	}
	private String value;
	private Integer width;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getWidth() {
		return width;
	}
}
