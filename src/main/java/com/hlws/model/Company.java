package com.hlws.model;

public class Company {

	private String id;
	private String uniqueShortName;
	private String companyName;
	private boolean active;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUniqueShortName() {
		return uniqueShortName;
	}
	public void setUniqueShortName(String uniqueShortName) {
		this.uniqueShortName = uniqueShortName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
