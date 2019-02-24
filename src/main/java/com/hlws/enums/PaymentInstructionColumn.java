package com.hlws.enums;

public enum PaymentInstructionColumn {
	BENEFICIARY("BENEFICIARY NAME", 6000),
	TRANSACTION_PARTICULARS("Transaction Particulars", 6000),
	BENEFICIARY_ACCOUNT("BENEFICIARY ACCOUNT", 6000),
	IFSC("IFSC", 4000),
	BRANCH("BRANCH", 5000),
	AMOUNT("Amount", 4000);
	
	PaymentInstructionColumn(String val, Integer width){
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
