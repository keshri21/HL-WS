package com.hlws.service;

import com.hlws.dal.IDoDAL;
import com.hlws.model.DO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DOService {

    @Autowired
    IDoDAL doRepository;

    public DO create(DO doObj){
        return doRepository.save(doObj);
    }

    public String update(DO doObj){
        doRepository.save(doObj);
        return doObj.getId();
    }

    public DO getOne(String id){
        return doRepository.findById(id);
    }

    public List<DO> getRunning(){
        return doRepository.findRunning();
    }

    public List<DO> getCompleted(){
        return doRepository.findCompleted();
    }

    public List<DO> getDOList(String filter){
    	switch(filter) {
	    	case "all": 
	    		return doRepository.getAll();
	    	case "completed": 
	    		return this.getCompleted();
	    	default: 
	    		return this.getRunning(); 
    	}
    }

}
