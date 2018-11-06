package com.hlws.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
public class Pan {
	private String id;
	private String panNo;
	private String panHolderName;
	private String panCopyLink;
	private String declarationLink;
	private boolean tds;
	private List<Account> accounts;
	private Long mobile;
	private String city;
	private String state;
	private Set<Vehicle> vehicles;
	
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPanHolderName() {
		return panHolderName;
	}
	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}

	public String getPanCopyLink() {
		return panCopyLink;
	}
	public void setPanCopyLink(String panCopyLink) {
		this.panCopyLink = panCopyLink;
	}
	public String getDeclarationLink() {
		return declarationLink;
	}
	public void setDeclarationLink(String declarationLink) {
		this.declarationLink = declarationLink;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<Vehicle> getVehicles() {
		return vehicles == null ? new HashSet<>() : vehicles;
	}
	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public boolean isTds() {
		return tds;
	}
	public void setTds(boolean tds) {
		this.tds = tds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Pan [id=" + id + ", panNo=" + panNo + ", panHolderName=" + panHolderName + ", tds=" + tds
				+ ", accounts=" + accounts + ", mobile=" + mobile + ", vehicles=" + vehicles + "]";
	}
	
	
}
