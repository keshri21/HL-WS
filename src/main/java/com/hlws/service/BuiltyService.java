package com.hlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hlws.model.Builty;

@Service
public class BuiltyService {
	
	public Long createBuilty(Builty builty) throws Exception{
		// TODO call dao layer to store builty
		return 123l;
	}
	
	public List<Builty> getBuiltyList(String filterParam) throws Exception{
		List<Builty> list = new ArrayList<>();
		
		return list;
	}
	
	public void updateReceipt(List<Builty> builtyList) throws Exception{
		
	}
}
