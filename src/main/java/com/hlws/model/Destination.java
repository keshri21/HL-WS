package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

public class Destination {
	private String name;
	private List<Integer> freights;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getFreights() {
		return freights == null ? new ArrayList<>() : freights;
	}
	public void setFreights(List<Integer> freights) {
		this.freights = freights;
	}
	
	
}
