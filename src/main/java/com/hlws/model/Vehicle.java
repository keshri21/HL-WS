package com.hlws.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.util.AppConstants;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Vehicle implements Comparable<Vehicle>{
	
	private String vehicleNo;
	private String rcCopyLink;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date addedDate;
	private boolean isOldOwner;
	private String panNo;
	private String panHolderName;
	private Long mobile;
	
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
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
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
	
	@Override
	public int compareTo(Vehicle o) {
		if( o == null) {
			return 1;
		}else if(o.vehicleNo == null) {
			return 1;
		}else if(this.vehicleNo == null) {
			return -1;
		}
		return this.vehicleNo.compareTo(o.vehicleNo);
	}
	
	
	
	
}
