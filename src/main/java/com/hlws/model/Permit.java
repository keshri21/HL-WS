package com.hlws.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.util.AppConstants;
import com.hlws.util.AppUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Permit {

	private String id;
	private Long permitnumber;
	private Double quantity;
	private Double permitbalance;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date enddate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date createddate;
	private String createdby;
	private String lastmodifiedby;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date lastmodifieddate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPermitnumber() {
		return permitnumber;
	}
	public void setPermitnumber(Long permitnumber) {
		this.permitnumber = permitnumber;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = AppUtil.formatDecimalValue(quantity);
	}
	
	public Double getPermitbalance() {
		return permitbalance;
	}
	public void setPermitbalance(Double permitBalance) {
		this.permitbalance = AppUtil.formatDecimalValue(permitBalance);
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getLastmodifiedby() {
		return lastmodifiedby;
	}
	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}
	public Date getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(Date lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	
	
}
