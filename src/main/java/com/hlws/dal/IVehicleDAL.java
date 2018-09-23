package com.hlws.dal;

import com.hlws.model.Vehicle;

import java.util.List;

public interface IVehicleDAL {

    Vehicle save(Vehicle vehicle);
    void updateIsOld(String vehicleNo);
    //Vehicle insert(Vehicle vehicle);
    List<Vehicle> findBySearchText(String searchText);
}
