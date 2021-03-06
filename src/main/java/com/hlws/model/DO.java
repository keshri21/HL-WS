package com.hlws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hlws.dto.UserDTO;
import com.hlws.util.AppConstants;
import com.hlws.util.AppUtil;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DO {

	private String id;
	private Long bspDoNo;
	private Integer areaDoNo;
	private String doDisplay;
	private Integer auctionNo;
	private Double quantity;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date doDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date receivedDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date dueDate;
	private String size;
	private Party party;
	private List<DestinationParty> destinationparty;
	private List<Integer> permitNos;
	private String area;
	private String collary;
	private String grade;
	private String by;
	private UserDTO transporter;	// should be one with FIELD role
	private Double emd;
	private Double doAmt;
	private Double doAmtpmt;
	private Double doRate;
	private Double doRateTcs;
	private Double doBalance;
	private String withinOutSide;
	private Double quantityDeduction;
	private Double lepseQuantity;
	private String doStatus;
	private Double refundAmt;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date refundDate;
	private Double emdAmt;
	private String website;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_FORMAT, timezone="IST")
	private Date finishDate;
	private String remarks;
	private List<Integer> inAdvanceLimit;
	private List<String> subTransporter;
	private List<String> freightToBePaidBy;
	private List<String> otBuiltyCompany;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_TIME_FORMAT, timezone="IST")
	private Date createdDateTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=AppConstants.DATE_TIME_FORMAT, timezone="IST")
	private Date lastModifiedDateTime;
	private String createdBy;
	private String createdByFullName;
	private String lastModifiedBy;
	private String doCopy;
	
	public Long getBspDoNo() {
		return bspDoNo;
	}
	public void setBspDoNo(Long bspDoNo) {
		this.bspDoNo = bspDoNo;
	}
	public Integer getAreaDoNo() {
		return areaDoNo;
	}
	public void setAreaDoNo(Integer areaDoNo) {
		this.areaDoNo = areaDoNo;
	}
	
	public String getDoDisplay() {
		return doDisplay == null ? buildDoDisplay() : doDisplay;
	}
	public void setDoDisplay(String doDisplay) {
		this.doDisplay = doDisplay;
	}
	public Double getQuantity() {
		return quantity;
	}
	
	public Integer getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(Integer auctionNo) {
		this.auctionNo = auctionNo;
	}
	public void setQuantity(Double quantity) {
		this.quantity = AppUtil.formatDecimalValue(quantity);
	}
	public Date getDoDate() {
		return doDate;
	}
	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	public List<DestinationParty> getDestinationparty() {
		return CollectionUtils.isEmpty(destinationparty) ? new ArrayList<>() : destinationparty;
	}
	public void setDestinationparty(List<DestinationParty> destinationParty) {
		this.destinationparty = destinationParty;
	}
	public List<Integer> getPermitNos() {
		return permitNos;
	}
	public void setPermitNos(List<Integer> permitNos) {
		this.permitNos = permitNos;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCollary() {
		return collary;
	}
	public void setCollary(String collary) {
		this.collary = collary;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public UserDTO getTransporter() {
		return transporter;
	}
	public void setTransporter(UserDTO transporter) {
		this.transporter = transporter;
	}
	public Double getEmd() {
		return emd;
	}
	public void setEmd(Double emd) {
		this.emd = AppUtil.formatDecimalValue(emd);
	}
	public Double getDoAmt() {
		return doAmt;
	}
	public void setDoAmt(Double doAmt) {
		this.doAmt = AppUtil.formatDecimalValue(doAmt);
	}
	public Double getDoAmtpmt() {
		return doAmtpmt;
	}
	public void setDoAmtpmt(Double doAmtpmt) {
		this.doAmtpmt = AppUtil.formatDecimalValue(doAmtpmt);
	}
	public Double getDoRate() {
		return doRate;
	}
	public void setDoRate(Double doRate) {
		this.doRate = AppUtil.formatDecimalValue(doRate);
	}
	public Double getDoRateTcs() {
		return doRateTcs;
	}
	public void setDoRateTcs(Double doRateTcs) {
		this.doRateTcs = AppUtil.formatDecimalValue(doRateTcs);
	}
	
	public Double getDoBalance() {
		return doBalance == null ? this.getQuantity() : doBalance;
	}
	public void setDoBalance(Double doBalance) {
		this.doBalance = AppUtil.formatDecimalValue(doBalance);
	}
	public String getWithinOutSide() {
		return withinOutSide;
	}
	public void setWithinOutSide(String withinOutSide) {
		this.withinOutSide = withinOutSide;
	}
	public Double getQuantityDeduction() {
		return quantityDeduction == null ? 0 : quantityDeduction;
	}
	public void setQuantityDeduction(Double quantityDeduction) {
		this.quantityDeduction = AppUtil.formatDecimalValue(quantityDeduction);
	}
	public Double getLepseQuantity() {
		return lepseQuantity == null ? 0 : lepseQuantity;
	}
	public void setLepseQuantity(Double lepseQuantity) {
		this.lepseQuantity = AppUtil.formatDecimalValue(lepseQuantity);
	}
	public String getDoStatus() {
		return doStatus;
	}
	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}
	public Double getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(Double refundAmt) {
		this.refundAmt = AppUtil.formatDecimalValue(refundAmt);
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public Double getEmdAmt() {
		return emdAmt;
	}
	public void setEmdAmt(Double emdAmt) {
		this.emdAmt = AppUtil.formatDecimalValue(emdAmt);
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<Integer> getInAdvanceLimit() {
		return CollectionUtils.isEmpty(inAdvanceLimit) ? new ArrayList<>() : inAdvanceLimit;
	}
	public void setInAdvanceLimit(List<Integer> inAdvanceLimit) {
		this.inAdvanceLimit = inAdvanceLimit;
	}
		
	public List<String> getSubTransporter() {
		return CollectionUtils.isEmpty(subTransporter) ? new ArrayList<>() : subTransporter;
	}
	public void setSubTransporter(List<String> subTransporter) {
		this.subTransporter = subTransporter;
	}
	public List<String> getFreightToBePaidBy() {
		return CollectionUtils.isEmpty(freightToBePaidBy) ? new ArrayList<>() : freightToBePaidBy;
	}
	public void setFreightToBePaidBy(List<String> freightToBePaidBy) {
		this.freightToBePaidBy = freightToBePaidBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getOtBuiltyCompany() {
		return CollectionUtils.isEmpty(otBuiltyCompany) ? new ArrayList<>() : otBuiltyCompany;
	}
	public void setOtBuiltyCompany(List<String> otBuiltyCompany) {
		this.otBuiltyCompany = otBuiltyCompany;
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
	
	public String getCreatedByFullName() {
		return createdByFullName;
	}
	public void setCreatedByFullName(String createdByFullName) {
		this.createdByFullName = createdByFullName;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModified) {
		this.lastModifiedBy = lastModified;
	}
	public String getDoCopy() {
		return doCopy;
	}
	public void setDoCopy(String doCopy) {
		this.doCopy = doCopy;
	}
	private String buildDoDisplay() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.areaDoNo)
				.append("/")
				.append(this.bspDoNo)
				.append(" - ")
				.append(this.collary)
				.append(" - ")
				.append(this.quantity);
		
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "DO [id=" + id + ", dueDate=" + dueDate +", doDisplay=" + getDoDisplay() + "]";
	}
	
	
	
}
