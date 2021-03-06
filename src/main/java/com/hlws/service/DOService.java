package com.hlws.service;

import com.hlws.dal.IDoDAL;
import com.hlws.exceptions.InvalidDataException;
import com.hlws.model.DO;
import com.hlws.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DOService {

    @Autowired
    IDoDAL doRepository;

    public DO create(DO doObj) throws InvalidDataException{
    	
    	if(doRepository.checkDoByBspDo(doObj.getBspDoNo())) {
    		throw new InvalidDataException("DO with same BSP DO number already exists");
    	}
    	doObj.setCreatedDateTime(new Date());
    	doObj.setCreatedBy(AppUtil.getLoggedInUser().getUsername());
    	doObj.setCreatedByFullName(AppUtil.getLoggedInUser().getName());
    	doObj.setDoBalance(doObj.getQuantity());
        return doRepository.save(doObj);
    }

    public String update(DO doObj){
    	doObj.setLastModifiedDateTime(new Date());
    	doObj.setLastModifiedBy(AppUtil.getLoggedInUser().getUsername());
    	DO origDO = doRepository.findById(doObj.getId());
    	
    	//check if quantity deduction or lapse quantity changed
    	//if changed then we need to update the DO balance with the difference in change
    	double doBalance = origDO.getDoBalance();
    	if(origDO.getQuantityDeduction() != doObj.getQuantityDeduction()) {    		
    		double qtyDeductionDiff = doObj.getQuantityDeduction() - origDO.getQuantityDeduction();
    		doBalance = doBalance - qtyDeductionDiff;
    	}
    	if(origDO.getLepseQuantity() != doObj.getLepseQuantity()) {    		
    		double lepseQuantityDiff = doObj.getLepseQuantity() - origDO.getLepseQuantity();
    		doBalance = doBalance - lepseQuantityDiff;    		
    	}
    	doObj.setDoBalance(doBalance < 0 ? 0 : doBalance);
    	
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
