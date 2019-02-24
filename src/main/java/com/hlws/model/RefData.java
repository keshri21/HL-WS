package com.hlws.model;

import java.util.ArrayList;
import java.util.List;

public class RefData {
	private List<Party> partyList;
	private List<Area> areaList;
	private List<String> states;
	private FreightRefData freightData;
	public List<Party> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<Party> partyList) {
		this.partyList = partyList;
	}
	public List<Area> getAreaList() {
		return areaList == null ? new ArrayList<>() : areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	public List<String> getStates() {
		return states == null ? new ArrayList<>() : states;
	}
	public void setStates(List<String> states) {
		this.states = states;
	}
	public FreightRefData getFreightData() {
		return freightData;
	}
	public void setFreightData(FreightRefData freightData) {
		this.freightData = freightData;
	}
	
}
