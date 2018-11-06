package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

public class RefData {
	private List<Party> partyList;
	private List<String> areaList;
	private List<String> collaryList;
	private List<String> states;
	public List<Party> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<Party> partyList) {
		this.partyList = partyList;
	}
	public List<String> getAreaList() {
		return areaList == null ? new ArrayList<>() : areaList;
	}
	public void setAreaList(List<String> areaList) {
		this.areaList = areaList;
	}
	public List<String> getCollaryList() {
		return collaryList == null ? new ArrayList<>() : collaryList;
	}
	public void setCollaryList(List<String> collaryList) {
		this.collaryList = collaryList;
	}
	public List<String> getStates() {
		return states == null ? new ArrayList<>() : states;
	}
	public void setStates(List<String> states) {
		this.states = states;
	}
	
}
