package com.hlws.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlws.util.AppConstants;

@Document
public class Builty implements Comparable<Builty> {

	private String id;
	private String builtyNo;
	private String doId;
	private String doDisplay;
	private String party;
	private String destination;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date builtyDate;
	private String otBuiltyCompany;
	private Integer otBuiltyNumber;
	private String vehicleNo;
	private Double doOpeningBalance;
	private Integer inAdvance;
	private Integer outAdvance;
	private Integer diesel;
	private String pumpName;
	private Integer freight;
	private Integer totalCashAdvance;
	private Integer totalAdvance;
	private Long permitNo;
	private Double permitBalance;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date permitEndDate;
	private Long igpNo;
	private Long invoiceNo;
	private Double invoiceValue;
	private String driverName;
	private Long driverMobile;
	private Double grossWeight;
	private Double tierWeight;
	private Double doClosingBalance;
	private User transporter;
	private User subTransporter;
	private String waybillNo;
	private String tpNo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date receivedDate;
	private Double receivedQuantity;
	private Double netWeight;
	private Double refund;
	private Double assesibleValue;
	private String freightToBePaidBy;
	private Long savedReferenceNumber;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_TIME_FORMAT, timezone="IST")
	private Date createdDateTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_TIME_FORMAT, timezone="IST")
	private Date lastModifiedDateTime;
	private String createdBy;
	private String lastModifiedBy;
	private boolean approved;
	private boolean deleted;
	private boolean freightGenerated;
	private boolean paymentInstructionDone;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_TIME_FORMAT, timezone="IST")
	private Date paymentInstructionDateTime;
	private Double freightBill;
	private Double deductionRate;
	private Double shortageLimit;
	private Double freightComission;
	
	public String getBuiltyNo() {
		return builtyNo;
	}
	public void setBuiltyNo(String builtyNo) {
		this.builtyNo = builtyNo;
	}
	public String getDoId() {
		return doId;
	}
	public void setDoId(String doId) {
		this.doId = doId;
	}
	public String getDoDisplay() {
		return doDisplay;
	}
	public void setDoDisplay(String doDisplay) {
		this.doDisplay = doDisplay;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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
	public Double getDoOpeningBalance() {
		return doOpeningBalance;
	}
	public void setDoOpeningBalance(Double openingBalance) {
		this.doOpeningBalance = openingBalance;
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
	
	public Integer getTotalCashAdvance() {
		return totalCashAdvance;
	}
	public void setTotalCashAdvance(Integer totalCashAdvance) {
		this.totalCashAdvance = totalCashAdvance;
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
	public Date getPermitEndDate() {
		return permitEndDate;
	}
	public void setPermitEndDate(Date permitEndDate) {
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
	public Double getDoClosingBalance() {
		return doClosingBalance;
	}
	public void setDoClosingBalance(Double doClosingBalance) {
		this.doClosingBalance = doClosingBalance;
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
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
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
		return savedReferenceNumber == null ? -1 : savedReferenceNumber;
	}

	public void setSavedReferenceNumber(Long savedReferenceNumber) {
		this.savedReferenceNumber = savedReferenceNumber;
	}
	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isFreightGenerated() {
		return freightGenerated;
	}
	public void setFreightGenerated(boolean freightGenerated) {
		this.freightGenerated = freightGenerated;
	}
	public boolean isPaymentInstructionDone() {
		return paymentInstructionDone;
	}
	public void setPaymentInstructionDone(boolean paymentInstructionDone) {
		this.paymentInstructionDone = paymentInstructionDone;
	}
	public Double getFreightBill() {
		return freightBill;
	}
	public void setFreightBill(Double freightBill) {
		this.freightBill = freightBill;
	}
	public Double getDeductionRate() {
		return deductionRate;
	}
	public void setDeductionRate(Double deductionRate) {
		this.deductionRate = deductionRate;
	}
	public Double getShortageLimit() {
		return shortageLimit;
	}
	public void setShortageLimit(Double shortageLimit) {
		this.shortageLimit = shortageLimit;
	}
	public Double getFreightComission() {
		return freightComission;
	}
	public void setFreightComission(Double freightComission) {
		this.freightComission = freightComission;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.id +
				", " + this.builtyNo +
				", " + this.receivedDate +")";
	}
	
	@Override
	public int compareTo(Builty o) {
		if( o == null) {
			return 1;
		}else if(o.builtyNo == null) {
			return 1;
		}else if(this.builtyNo == null) {
			return -1;
		}
		return this.builtyNo.compareTo(o.builtyNo);
	}
	
	
	
	
	
	
}
