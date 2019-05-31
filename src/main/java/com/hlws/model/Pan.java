package com.hlws.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.util.AppUtil;

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
	private Account primaryAccount;
	private Double extraPayment;
	
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
		
	
	public Double getExtraPayment() {
		return extraPayment == null ? 0 : extraPayment;
	}
	public void setExtraPayment(Double extraPayment) {
		this.extraPayment = AppUtil.formatDecimalValue(extraPayment);
	}
	public Account getPrimaryAccount() {
		Account primary = null;
					
		for(Account account : accounts) {
			if(account.isPrimary()) {
				primary = account;
				break;
			}
		}
		if(null == primary && CollectionUtils.isNotEmpty(accounts)) {
			primary =  this.accounts.get(0);
		}		
		return primary;
	}
	public void setPrimaryAccount(Account primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	@Override
	public String toString() {
		return "Pan [id=" + id + ", panNo=" + panNo + ", panHolderName=" + panHolderName + ", tds=" + tds
				+ ", accounts=" + accounts + ", mobile=" + mobile + ", vehicles=" + vehicles + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}else if(!(obj instanceof Pan)) {
			return false;
		}else {			
			return this.panNo.equals(((Pan)obj).panNo);
		}
		 
	}
	@Override
	public int hashCode() {
		int i = 37;		
		return this.panNo.hashCode() * i;
	}
	
	
	
	
}
