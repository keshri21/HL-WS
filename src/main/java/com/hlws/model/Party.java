package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Party {

	@Id
	private Integer id;
	private String name;
	private List<String> destinations;
	private List<FreightRange> freightRanges;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDestinations() {
		return destinations == null ? new ArrayList<String>() : destinations;
	}
	public void setDestinations(List<String> destinations) {
		this.destinations = destinations;
	}
	public List<FreightRange> getFreightRanges() {
		return freightRanges == null ? new ArrayList<>() : freightRanges;
	}
	public void setFreightRanges(List<FreightRange> freightRanges) {
		this.freightRanges = freightRanges;
	}
	
	
}
