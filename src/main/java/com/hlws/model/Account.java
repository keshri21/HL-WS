package com.hlws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {

	private String accountNo;
	private String accountHoldername;
	private String ifscCode;
	private String bankName;
	private String passbookLink;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountHoldername() {
		return accountHoldername;
	}
	public void setAccountHoldername(String accountHolderName) {
		this.accountHoldername = accountHolderName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPassbookLink() {
		return passbookLink;
	}
	public void setPassbookLink(String passbookLink) {
		this.passbookLink = passbookLink;
	}

	

}
