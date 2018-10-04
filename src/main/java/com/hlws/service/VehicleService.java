package com.hlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IVehicleDAL;
import com.hlws.model.Vehicle;

@Service
public class VehicleService {

	@Autowired
	IVehicleDAL vehicleRepository;
	
	public List<Vehicle> get(String searchText){
		return vehicleRepository.findBySearchText(searchText);
	}
}
