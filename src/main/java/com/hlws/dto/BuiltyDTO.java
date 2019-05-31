package com.hlws.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.util.AppConstants;
import com.hlws.util.AppUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuiltyDTO implements Comparable<BuiltyDTO>{
	private String id;
	private String panNo;
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
	private Double extraPayment;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
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
		this.receivedQuantity = AppUtil.formatDecimalValue(receivedQuantity);
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
		this.freightBill = AppUtil.formatDecimalValue(freightBill);
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
	
	public Double getExtraPayment() {
		return extraPayment;
	}
	public void setExtraPayment(Double extraPayment) {
		this.extraPayment = AppUtil.formatDecimalValue(extraPayment);
	}
	@Override
	public int compareTo(BuiltyDTO builty) {
		if(null == builty) {
			return 1;
		}		
		return this.builtyNo.compareTo(builty.getBuiltyNo());
	}
	
	
	
}
	
