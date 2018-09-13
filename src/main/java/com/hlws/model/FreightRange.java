package com.hlws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FreightRange {
	
	private Integer min;
	private Integer max;
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.min > 0 || this.max > 0) {
			return this.min + " - " + this.max;
		}
		return "-";
	}
	
}
