package com.hlws.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
public class Pan {
	private String id;
	private String panNo;
	private String panHolderName;
	private String fatherName;
	private String panCopyLink;
	private String declarationLink;
	private boolean tds;
	private List<Account> accounts;
	private Long mobile;
	private String address;
	private String city;
	private String district;
	private List<Vehicle> vehicles;
	
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
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
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
}
