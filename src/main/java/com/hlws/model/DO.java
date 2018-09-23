package com.hlws.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DO {

	private String id;
	private Integer bspDoNo;
	private Integer areaDoNo;
	private Integer auctionNo;
	private Double quantity;
	private String doDate;
	private String receivedDate;
	private String dueDate;
	private String size;
	private Party party;
	private List<String> destinations;
	private List<Party> destinationParty;
	private FreightRange freight;
	private Integer permissionNo;
	private String area;
	private String collary;
	private String grade;
	private String by;
	private String builtyCompany;
	private User transporter;	// should be one with FIELD role
	private Double emd;
	private Double doAmt;
	private Double doAmtpmt;
	private Double doRate;
	private Double doRateTcs;
	private String withinOutSide;
	private String disp;
	private Double liftedQuantity;
	private Double quantityDeduction;
	private Double lepseQuantity;
	private String doStatus;
	private Double refundAmt;
	private String refundDate;
	private Double emdAmt;
	private Double totalRefundAmt;
	private String website;
	private String finishDate;
	private String remarks;
	private List<Integer> inAdvanceLimit;
	private List<String> freightToBePaidBy;
	
	public Integer getBspDoNo() {
		return bspDoNo;
	}
	public void setBspDoNo(Integer bspDoNo) {
		this.bspDoNo = bspDoNo;
	}
	public Integer getAreaDoNo() {
		return areaDoNo;
	}
	public void setAreaDoNo(Integer areaDoNo) {
		this.areaDoNo = areaDoNo;
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
		this.quantity = quantity;
	}
	public String getDoDate() {
		return doDate;
	}
	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
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
	public List<String> getDestinations() {
		return CollectionUtils.isEmpty(destinations) ? new ArrayList<>() : destinations;
	}
	public void setDestinations(List<String> destinations) {
		this.destinations = destinations;
	}
	public List<Party> getDestinationParty() {
		return CollectionUtils.isEmpty(destinationParty) ? new ArrayList<>() : destinationParty;
	}
	public void setDestinationParty(List<Party> destinationParty) {
		this.destinationParty = destinationParty;
	}
	public FreightRange getFreight() {
		return freight;
	}
	public void setFreight(FreightRange freight) {
		this.freight = freight;
	}
	public Integer getPermissionNo() {
		return permissionNo;
	}
	public void setPermissionNo(Integer permissionNo) {
		this.permissionNo = permissionNo;
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
	public String getBuiltyCompany() {
		return builtyCompany;
	}
	public void setBuiltyCompany(String builtyCompany) {
		this.builtyCompany = builtyCompany;
	}
	public User getTransporter() {
		return transporter;
	}
	public void setTransporter(User transporter) {
		this.transporter = transporter;
	}
	public Double getEmd() {
		return emd;
	}
	public void setEmd(Double emd) {
		this.emd = emd;
	}
	public Double getDoAmt() {
		return doAmt;
	}
	public void setDoAmt(Double doAmt) {
		this.doAmt = doAmt;
	}
	public Double getDoAmtpmt() {
		return doAmtpmt;
	}
	public void setDoAmtpmt(Double doAmtpmt) {
		this.doAmtpmt = doAmtpmt;
	}
	public Double getDoRate() {
		return doRate;
	}
	public void setDoRate(Double doRate) {
		this.doRate = doRate;
	}
	public Double getDoRateTcs() {
		return doRateTcs;
	}
	public void setDoRateTcs(Double doRateTcs) {
		this.doRateTcs = doRateTcs;
	}
	public String getWithinOutSide() {
		return withinOutSide;
	}
	public void setWithinOutSide(String withinOutSide) {
		this.withinOutSide = withinOutSide;
	}
	public String getDisp() {
		return disp;
	}
	public void setDisp(String disp) {
		this.disp = disp;
	}
	public Double getLiftedQuantity() {
		return liftedQuantity;
	}
	public void setLiftedQuantity(Double liftedQuantity) {
		this.liftedQuantity = liftedQuantity;
	}
	public Double getQuantityDeduction() {
		return quantityDeduction;
	}
	public void setQuantityDeduction(Double quantityDeduction) {
		this.quantityDeduction = quantityDeduction;
	}
	public Double getLepseQuantity() {
		return lepseQuantity;
	}
	public void setLepseQuantity(Double lepseQuantity) {
		this.lepseQuantity = lepseQuantity;
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
		this.refundAmt = refundAmt;
	}
	public String getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}
	public Double getEmdAmt() {
		return emdAmt;
	}
	public void setEmdAmt(Double emdAmt) {
		this.emdAmt = emdAmt;
	}
	public Double getTotalRefundAmt() {
		return totalRefundAmt;
	}
	public void setTotalRefundAmt(Double totalRefundAmt) {
		this.totalRefundAmt = totalRefundAmt;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
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
}
