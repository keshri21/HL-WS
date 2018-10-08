package com.hlws.dal;

import com.hlws.model.Pan;

import java.util.List;

public interface IPanDAL extends IBaseDAL {
    Pan save(Pan pan);
    List<Pan> getAll();
    List<Pan> findBySearchText(String searchText);
    Pan getOne(String panNo);
}
