package com.hlws.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<Vehicle> getVehicles(String searchText, boolean includeOldOwner){
		List<Pan> panlist;
		switch (searchText) {
		case "all":
			panlist = vehicleRepository.getAllVehicles();
			break;
		default:
			panlist = vehicleRepository.getVehicles(searchText);
			break;
		}
    	List<Vehicle> vehicles = transformPanToVehicles(panlist);
    	return vehicles.stream().filter(vehicle -> 
			vehicle.getVehicleNo().contains(searchText)
		).filter(vehicle -> 
			includeOldOwner ? includeOldOwner : !vehicle.isOldOwner()
		).sorted().collect(Collectors.toList());
//    	Collections.sort(vehicles);
//    	return vehicles;
    }
    
    private List<Vehicle> transformPanToVehicles(final List<Pan> panlist){
    	List<Vehicle> vehicles = new ArrayList<>();
    	panlist.stream().forEach(pan -> {
    		pan.getVehicles().stream().forEach((vehicle) -> {
				vehicle.setPanNo(pan.getPanNo());
				vehicle.setPanHolderName(pan.getPanHolderName());
				vehicle.setMobile(pan.getMobile());
				vehicles.add(vehicle);
			});
    	});
    	return vehicles;
    }
}
