package com.hlws.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlws.util.AppConstants;

public class BuiltyDTO {
	private String id;
	private Double receivedQuantity;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date receivedDate;
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
	
	
}
