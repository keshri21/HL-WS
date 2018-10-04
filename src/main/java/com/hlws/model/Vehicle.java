package com.hlws.model;

import org.springframework.data.annotation.Id;

public class Vehicle {

	@Id
	private Long vehicleId;
	private String vehicleNo;
	private String rcNo;
	private String rcCopyLink;
	private String addedDate;
	private boolean isOldOwner;
	
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getRcNo() {
		return rcNo;
	}
	public void setRcNo(String rcNo) {
		this.rcNo = rcNo;
	}
	public String getRcCopyLink() {
		return rcCopyLink;
	}
	public void setRcCopyLink(String rcCopyLink) {
		this.rcCopyLink = rcCopyLink;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public boolean isOldOwner() {
		return isOldOwner;
	}

	public void setOldOwner(boolean old) {
		isOldOwner = old;
	}
}
