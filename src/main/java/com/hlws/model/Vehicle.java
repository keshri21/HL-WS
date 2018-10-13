package com.hlws.model;

public class Vehicle {
	
	private String vehicleNo;
	private String rcCopyLink;
	private String addedDate;
	private boolean isOldOwner;
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
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
