package com.hlws.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Builty {

	private Integer builtyNo;
	private Integer doId;
	private String doDisplay;
	private Party party;
	private Date builtyDate;
	private String otBuiltyCompany;
	private Integer otBuiltyNumber;
	private String vehicleNo;
	private Double quantity;
	private Integer inAdvance;
	private Integer outAdvance;
	private Integer diesel;
	private String pumpName;
	private Integer freight;
	private Integer totalAdvance;
	private Integer permitNo;
	private Integer permitBalance;
	private Date permitEndDate;
	private Integer igpNo;
	private Integer invoiceNo;
	private Double invoiceValue;
	private String driverName;
	private Integer driverMobile;
	private Double grossWeight;
	private Double doBalance;
	private User transporter;
	private User subTransporter;
	private String waybillNo;
	private String tpNo;
	
	public Integer getBuiltyNo() {
		return builtyNo;
	}
	public void setBuiltyNo(Integer builtyNo) {
		this.builtyNo = builtyNo;
	}
	public Integer getDoId() {
		return doId;
	}
	public void setDoId(Integer doId) {
		this.doId = doId;
	}
	public String getDoDisplay() {
		return doDisplay;
	}
	public void setDoDisplay(String doDisplay) {
		this.doDisplay = doDisplay;
	}
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	public Date getBuiltyDate() {
		return builtyDate;
	}
	public void setBuiltyDate(Date builtyDate) {
		this.builtyDate = builtyDate;
	}
	public String getOtBuiltyCompany() {
		return otBuiltyCompany;
	}
	public void setOtBuiltyCompany(String otBuiltyCompany) {
		this.otBuiltyCompany = otBuiltyCompany;
	}
	public Integer getOtBuiltyNumber() {
		return otBuiltyNumber;
	}
	public void setOtBuiltyNumber(Integer otBuiltyNumber) {
		this.otBuiltyNumber = otBuiltyNumber;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getInAdvance() {
		return inAdvance;
	}
	public void setInAdvance(Integer inAdvance) {
		this.inAdvance = inAdvance;
	}
	public Integer getOutAdvance() {
		return outAdvance;
	}
	public void setOutAdvance(Integer outAdvance) {
		this.outAdvance = outAdvance;
	}
	public Integer getDiesel() {
		return diesel;
	}
	public void setDiesel(Integer diesel) {
		this.diesel = diesel;
	}
	public String getPumpName() {
		return pumpName;
	}
	public void setPumpName(String pumpName) {
		this.pumpName = pumpName;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Integer getTotalAdvance() {
		return totalAdvance;
	}
	public void setTotalAdvance(Integer totalAdvance) {
		this.totalAdvance = totalAdvance;
	}
	public Integer getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(Integer permitNo) {
		this.permitNo = permitNo;
	}
	public Integer getPermitBalance() {
		return permitBalance;
	}
	public void setPermitBalance(Integer permitBalance) {
		this.permitBalance = permitBalance;
	}
	public Date getPermitEndDate() {
		return permitEndDate;
	}
	public void setPermitEndDate(Date permitEndDate) {
		this.permitEndDate = permitEndDate;
	}
	public Integer getIgpNo() {
		return igpNo;
	}
	public void setIgpNo(Integer igpNo) {
		this.igpNo = igpNo;
	}
	public Integer getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Double getInvoiceValue() {
		return invoiceValue;
	}
	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Integer getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(Integer driverMobile) {
		this.driverMobile = driverMobile;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getDoBalance() {
		return doBalance;
	}
	public void setDoBalance(Double doBalance) {
		this.doBalance = doBalance;
	}
	public User getTransporter() {
		return transporter;
	}
	public void setTransporter(User transporter) {
		this.transporter = transporter;
	}
	public User getSubTransporter() {
		return subTransporter;
	}
	public void setSubTransporter(User subTransporter) {
		this.subTransporter = subTransporter;
	}
	public String getWaybillNo() {
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public String getTpNo() {
		return tpNo;
	}
	public void setTpNo(String tpNo) {
		this.tpNo = tpNo;
	}
	
	
	
}
