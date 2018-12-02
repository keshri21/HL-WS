package com.hlws.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPanDAL;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;

@Service
public class VehicleService {

	@Autowired
	IPanDAL vehicleRepository;
	
	/*public List<Vehicle> get(String searchText){
		return vehicleRepository.findBySearchText(searchText);
	}*/
	
	public List<Vehicle> getAllVehicles(){
    	List<Pan> panlist = vehicleRepository.getAllVehicles();
    	
    	return transformPanToVehicles(panlist);
    }
    
    private List<Vehicle> transformPanToVehicles(List<Pan> panlist){
    	List<Vehicle> vehicles = new ArrayList<>();
    	for (Pan pan : panlist) {
			if(CollectionUtils.isNotEmpty(pan.getVehicles())) {
				for(Vehicle vehicle : pan.getVehicles()) {
					if(!vehicle.isOldOwner()) {
						vehicle.setPanNo(pan.getPanNo());
						vehicle.setPanHolderName(pan.getPanHolderName());
						vehicle.setMobile(pan.getMobile());
						vehicles.add(vehicle);
					}
				}
			}
		}
    	Collections.sort(vehicles);
    	return vehicles;
    }
}
