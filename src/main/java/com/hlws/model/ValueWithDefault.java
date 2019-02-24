package com.hlws.model;

public class ValueWithDefault<T> {
	private T value;
	private Boolean defaultValue;
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Boolean getDefaultValue() {
		return defaultValue == null ? false : defaultValue;
	}
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}
