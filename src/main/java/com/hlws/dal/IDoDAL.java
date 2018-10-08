package com.hlws.dal;

import com.hlws.model.DO;

import java.util.List;

public interface IDoDAL extends IBaseDAL{
    DO save(DO doObj);
    List<DO> findRunning();
    List<DO> findCompleted();
    List<DO> getAll();
    List<DO> getAllSelected(List<String> ids);
    DO findById(String id);
    void delete(DO doObj);
}
