package com.hlws.model;

import java.util.List;

public class FreightRefData {

	private List<ValueWithDefault<Double>> shortageLimit;
	private List<ValueWithDefault<Double>> deductionRate;
	private List<ValueWithDefault<Double>> commission;
	public List<ValueWithDefault<Double>> getShortageLimit() {
		return shortageLimit;
	}
	public void setShortageLimit(List<ValueWithDefault<Double>> shortageLimit) {
		this.shortageLimit = shortageLimit;
	}
	public List<ValueWithDefault<Double>> getDeductionRate() {
		return deductionRate;
	}
	public void setDeductionRate(List<ValueWithDefault<Double>> deductionRate) {
		this.deductionRate = deductionRate;
	}
	public List<ValueWithDefault<Double>> getCommission() {
		return commission;
	}
	public void setCommission(List<ValueWithDefault<Double>> commission) {
		this.commission = commission;
	}
	
	
}
