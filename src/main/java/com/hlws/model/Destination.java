package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Destination {
	private String name;
	private List<Integer> freight;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getFreight() {
		return freight == null ? new ArrayList<>() : freight;
	}
	public void setFreight(List<Integer> freight) {
		this.freight = freight;
	}
	
	
}
