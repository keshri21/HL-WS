package com.hlws.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IBuiltyDAL;
import com.hlws.dal.IPanDAL;
import com.hlws.model.Builty;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;

@Service
public class PanService {

    @Autowired
    private IPanDAL panRepository;
    
    @Autowired
    private IBuiltyDAL builtyRepository;

    public Pan save(Pan pan, boolean createFlag) throws Exception{
        /*if(CollectionUtils.isEmpty(pan.getVehicles())){
            throw new Exception("Vehicle number is mandatory with pan registration");
        }*/
        if(createFlag && getOne(pan.getPanNo()) != null) {
            throw new Exception("PAN already registered");
        }
        //List<Vehicle> vehicles = pan.getVehicles();
        pan.setPanNo(pan.getPanNo().toUpperCase());
        /*for(Vehicle vehicle : vehicles){
            // this means new vehicle is being added to pan. So put added date and update idOld flag if same vehicle is already present against different pan
            if(vehicle.getAddedDate() == null){
                vehicle.setAddedDate(new Date());
                vehicleRepository.updateOwner(vehicle.getVehicleNo());
            }
           // vehicleRepository.save(vehicle);
        }*/
        return panRepository.save(pan);
    }

    public Pan getOne(String panNo){
        return panRepository.getOne(panNo.toUpperCase());
    }
    
    public List<Pan> getAll(){
    	return panRepository.getAll();
    }

    public void updatevehicles(String pan, List<Vehicle> vehicles) throws Exception{
    	Set<Vehicle> uniqueVehicles = new HashSet<>();
    	for (Vehicle vehicle : vehicles) {
			if(!uniqueVehicles.add(vehicle)) {
				throw new Exception("Vehicle number is already linked against this PAN");
			}
		}
    	for (Vehicle vehicle : uniqueVehicles) {
    		// if added date is null means new vehicle is being added
    		if(vehicle.getAddedDate() == null){
                vehicle.setAddedDate(new Date());
                vehicle.setOldOwner(false);
                vehicle.setVehicleNo(vehicle.getVehicleNo().toUpperCase());
                //mark any vehicle with same number as oldowner before adding new entry
                panRepository.updateVehicleOwner(vehicle.getVehicleNo());
            }
		}
    	
    	panRepository.updateVehicles(pan.toUpperCase(), uniqueVehicles);
    }
    
    public List<Pan> getVehiclesBySearchText(String searchText){
    	return panRepository.getVehicles(searchText);
    }
    
    public boolean ifVehicleCanBeDeleted(String vehicleno) {
    	Builty builty = builtyRepository.findBuiltyByVehicleNo(vehicleno);
    	return builty == null ? true : false;
    }
}
