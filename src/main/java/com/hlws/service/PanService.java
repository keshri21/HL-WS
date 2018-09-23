package com.hlws.service;

import com.hlws.dal.IPanDAL;
import com.hlws.dal.IVehicleDAL;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.hlws.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class PanService {

    @Autowired
    private IPanDAL panRepository;

    @Autowired
    private IVehicleDAL vehicleRepository;

    public Pan save(Pan pan, boolean update) throws Exception{
        if(CollectionUtils.isEmpty(pan.getVehicles())){
            throw new Exception("Vehicle number is mandatory to with pan registration");
        }
        if(!update && getOne(pan.getPanNo()) != null) {
            throw new Exception("PAN already registered");
        }
        List<Vehicle> vehicles = pan.getVehicles();

        for(Vehicle vehicle : vehicles){
            // this means new vehicle is being added to pan. So put added date and update idOld flag if same vehicle is already present against different pan
            if(vehicle.getAddedDate() == null){
                vehicle.setAddedDate(DateUtil.format(new Date()));
                vehicleRepository.updateIsOld(vehicle.getVehicleNo());
            }
           // vehicleRepository.save(vehicle);
        }
        return panRepository.save(pan);
    }

    public Pan getOne(String panNo){
        return panRepository.getOne(panNo);
    }


}
