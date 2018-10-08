package com.hlws.dal;

import com.hlws.model.Vehicle;

import java.util.List;

public interface IVehicleDAL extends IBaseDAL {

    Vehicle save(Vehicle vehicle);
    void updateOwner(String vehicleNo);
    //Vehicle insert(Vehicle vehicle);
    List<Vehicle> findBySearchText(String searchText);
}
