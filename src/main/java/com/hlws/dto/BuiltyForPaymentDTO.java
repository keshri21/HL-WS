package com.hlws.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlws.util.AppConstants;

public class BuiltyForPaymentDTO implements Comparable<BuiltyForPaymentDTO> {
	private String builtyNo;
	private Double receivedQuantity;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date receivedDate;
	private Double freightBill;
	private String vehicleNo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date builtyDate;
	private String vehicleOwner;
	private boolean isBankDtlsAvailable;
	
	public String getBuiltyNo() {
		return builtyNo;
	}
	public void setBuiltyNo(String builtyNo) {
		this.builtyNo = builtyNo;
	}
	public Double getReceivedQuantity() {
		return receivedQuantity;
	}
	public void setReceivedQuantity(Double receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Double getFreightBill() {
		return freightBill;
	}
	public void setFreightBill(Double freightBill) {
		this.freightBill = freightBill;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Date getBuiltyDate() {
		return builtyDate;
	}
	public void setBuiltyDate(Date builtyDate) {
		this.builtyDate = builtyDate;
	}
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public boolean isBankDtlsAvailable() {
		return isBankDtlsAvailable;
	}
	public void setBankDtlsAvailable(boolean isBankDtlsAvailable) {
		this.isBankDtlsAvailable = isBankDtlsAvailable;
	}
	@Override
	public int compareTo(BuiltyForPaymentDTO builty) {
		if(null == builty) {
			return 1;
		}		
		return this.builtyNo.compareTo(builty.getBuiltyNo());
	}
	
	
	

}
