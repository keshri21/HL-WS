package com.hlws.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPanDAL;
import com.hlws.dal.IVehicleDAL;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;

@Service
public class PanService {

    @Autowired
    private IPanDAL panRepository;

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
    	panRepository.updateVehicles(pan.toUpperCase(), uniqueVehicles);
    }
    
    public List<Pan> getVehiclesBySearchText(String searchText){
    	return panRepository.getVehicles(searchText);
    }
}
