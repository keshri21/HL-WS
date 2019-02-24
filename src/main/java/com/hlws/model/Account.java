package com.hlws.model;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {

	private String accountNo;
	private String accountHoldername;
	private String ifscCode;
	private String bankName;
	private Boolean isPassbookAvailable;
	private String passbookLink;
	private String branchName;
	private boolean isPrimary;
	
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
	public Boolean isPassbookAvailable() {
		return StringUtils.isEmpty(this.passbookLink) ? false : true;
	}
	/*public void setPassbookavailable(Boolean passbookavailable) {
		this.passbookavailable = passbookavailable;
	}*/
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public void setPassbookAvailable(Boolean passbookAvailable) {
		this.isPassbookAvailable = passbookAvailable;
	}
	
	

}
