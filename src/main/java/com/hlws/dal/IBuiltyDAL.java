package com.hlws.dal;

import com.hlws.dto.BuiltyDTO;
import com.hlws.model.Builty;
import com.hlws.model.DO;

import java.util.List;

public interface IBuiltyDAL {
    Builty save(Builty builty);

    List<Builty> findRunning();
    List<Builty> findCompleted();
    List<Builty> getAll();
    List<Builty> getAllSelected(List<String> ids);
    Builty findById(String id);
    Builty saveTemp(Builty builty);
    List<Builty> getAllFromTemp();
    Builty getOneFromTemp(String id);
    void removeFromTemp(Builty builty);
    void updateReceipt(List<BuiltyDTO> builtyList);
    void approve(String id);
}
