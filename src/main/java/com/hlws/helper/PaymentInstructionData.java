package com.hlws.helper;

import java.util.Map;

import com.hlws.model.Pan;

public class PaymentInstructionData {
	
	private Map<Pan, Double> instructionMap;
	//deduction map is used to adjust any extra payment from previous bilties
	private Map<String, Double> deductionMap;
	public Map<Pan, Double> getInstructionMap() {
		return instructionMap;
	}
	public void setInstructionMap(Map<Pan, Double> instructionMap) {
		this.instructionMap = instructionMap;
	}
	public Map<String, Double> getDeductionMap() {
		return deductionMap;
	}
	public void setDeductionMap(Map<String, Double> deductionMap) {
		this.deductionMap = deductionMap;
	}
	
	

}
