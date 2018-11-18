package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Area implements Comparable<Area>{
	
	@Id
	private String id;
	private String name;
	private List<String> collaries;
	
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
	public List<String> getCollaries() {
		return CollectionUtils.isEmpty(collaries) ? new ArrayList<>() : collaries;
	}
	public void setCollaries(List<String> collaries) {
		this.collaries = collaries;
	}

	@Override
	public int compareTo(Area o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o != null ? o.name : null);
	}
	
	
}
