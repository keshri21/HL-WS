package com.hlws.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.util.AppConstants;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Vehicle {
	
	private String vehicleNo;
	private String rcCopyLink;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date addedDate;
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

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public boolean isOldOwner() {
		return isOldOwner;
	}

	public void setOldOwner(boolean old) {
		isOldOwner = old;
	}
	@Override
	public String toString() {
		return "Vehicle [vehicleNo=" + vehicleNo + ", addedDate=" + addedDate + ", isOldOwner=" + isOldOwner + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicleNo == null) ? 0 : vehicleNo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (vehicleNo == null) {
			if (other.vehicleNo != null)
				return false;
		} else if (!vehicleNo.equals(other.vehicleNo))
			return false;
		return true;
	}
	
	
	
	
}
