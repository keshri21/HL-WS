package com.hlws.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
public class Builty {

	private String id;
	private Long builtyNo;
	private Long doId;
	private String doDisplay;
	private Party party;
	private String builtyDate;
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
	private Long permitNo;
	private Double permitBalance;
	private String permitEndDate;
	private Long igpNo;
	private Long invoiceNo;
	private Double invoiceValue;
	private String driverName;
	private Long driverMobile;
	private Double grossWeight;
	private Double tierWeight;
	private Double doBalance;
	private User transporter;
	private User subTransporter;
	private String waybillNo;
	private String tpNo;
	private String receivedDate;
	private Double receivedQuantity;
	private Double netWeight;
	private Double refund;
	private Double assesibleValue;
	private String freightToBePaidBy;
	private Integer inAdvanceLimit;
	private Long savedReferenceNumber;
	
	public Long getBuiltyNo() {
		return builtyNo;
	}
	public void setBuiltyNo(Long builtyNo) {
		this.builtyNo = builtyNo;
	}
	public Long getDoId() {
		return doId;
	}
	public void setDoId(Long doId) {
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
	public String getBuiltyDate() {
		return builtyDate;
	}
	public void setBuiltyDate(String builtyDate) {
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
	public Long getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(Long permitNo) {
		this.permitNo = permitNo;
	}
	public Double getPermitBalance() {
		return permitBalance;
	}
	public void setPermitBalance(Double permitBalance) {
		this.permitBalance = permitBalance;
	}
	public String getPermitEndDate() {
		return permitEndDate;
	}
	public void setPermitEndDate(String permitEndDate) {
		this.permitEndDate = permitEndDate;
	}
	public Long getIgpNo() {
		return igpNo;
	}
	public void setIgpNo(Long igpNo) {
		this.igpNo = igpNo;
	}
	public Long getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Long invoiceNo) {
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
	public Long getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(Long driverMobile) {
		this.driverMobile = driverMobile;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getTierWeight() {
		return tierWeight;
	}
	public void setTierWeight(Double tierWeight) {
		this.tierWeight = tierWeight;
	}
	public Integer getInAdvanceLimit() {
		return inAdvanceLimit;
	}
	public void setInAdvanceLimit(Integer inAdvanceLimit) {
		this.inAdvanceLimit = inAdvanceLimit;
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
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Double getReceivedQuantity() {
		return receivedQuantity;
	}
	public void setReceivedQuantity(Double receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public Double getAssesibleValue() {
		return assesibleValue;
	}
	public void setAssesibleValue(Double assesibleValue) {
		this.assesibleValue = assesibleValue;
	}
	public String getFreightToBePaidBy() {
		return freightToBePaidBy;
	}
	public void setFreightToBePaidBy(String freightToBePaidBy) {
		this.freightToBePaidBy = freightToBePaidBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSavedReferenceNumber() {
		return savedReferenceNumber;
	}

	public void setSavedReferenceNumber(Long savedReferenceNumber) {
		this.savedReferenceNumber = savedReferenceNumber;
	}
}
