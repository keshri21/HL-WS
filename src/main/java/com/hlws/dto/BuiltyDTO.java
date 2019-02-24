package com.hlws.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlws.util.AppConstants;

public class BuiltyDTO {
	private String id;
	private Double receivedQuantity;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date receivedDate;
	private Double freightBill;
	private String vehicleNo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date builtyDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
	
}
	
