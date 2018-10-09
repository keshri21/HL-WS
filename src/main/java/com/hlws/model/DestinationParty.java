package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DestinationParty {

	private String id;
	private String name;
	private List<Destination> destinations;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Destination> getDestinations() {
		return destinations == null ? new ArrayList<>() : destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
}
