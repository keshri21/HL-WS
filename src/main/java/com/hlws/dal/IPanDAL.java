package com.hlws.dal;

import com.hlws.model.Pan;
import com.hlws.model.Vehicle;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IPanDAL extends IBaseDAL {
    Pan save(Pan pan);
    List<Pan> getAll();
    List<Pan> findBySearchText(String searchText);
    Pan getByPanNo(String panNo);
    void updateVehicles(String pan, Set<Vehicle> updatedList);
    List<Pan> getVehicles(String searchText);
    List<Pan> getAllVehicles();
    void updateVehicleOwner(String vehicleNo);
    Pan getVehicleOwner(String vehicleNo, Date builtyDate);
    void updateExtraPayment(String panNo, Double extraPayment);
}
