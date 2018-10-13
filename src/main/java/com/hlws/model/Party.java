package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Party implements Comparable<Party>{

	@Id
	private String id;
	private String name;
	private List<String> destinations;
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
	public List<String> getDestinations() {
		return destinations == null ? new ArrayList<>() : destinations;
	}
	public void setDestinations(List<String> destinations) {
		this.destinations = destinations;
	}
	@Override
	public int compareTo(Party o) {
		// TODO Auto-generated method stub
		
		return this.name.compareTo(o != null ? o.name : null);
	}
	
	
}
