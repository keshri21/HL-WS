package com.hlws.dal;

import java.util.List;

import com.hlws.dto.BuiltyDTO;
import com.hlws.model.Builty;
import com.hlws.util.AppConstants;
import com.hlws.util.AppUtil;

public interface IBuiltyDAL extends IBaseDAL {
    Builty save(Builty builty);

    List<Builty> findRunning();
    List<Builty> findCompleted();
    List<Builty> getAll();
    List<Builty> getAllSelected(List<String> ids);
    Builty findById(String id);
    Builty saveTemp(Builty builty);
    List<Builty> getAllFromTemp();
    Builty getOneFromTemp(String id);
    List<Builty> getAllForDO(List<String> doIds);
    void removeFromTemp(Builty builty);
    void updateReceipt(List<BuiltyDTO> builtyList);
    void approve(String id);
    
    default String getTempCollectionName(String fixedCollectionName) {
    	StringBuilder builder = new StringBuilder(fixedCollectionName);
		
		return builder.append(AppConstants.COLLECTION_DELIMETER)
				.append("temp")
				.append(AppConstants.COLLECTION_DELIMETER)
				.append(AppUtil.getLoggedInUser().getCompanyId())
				.toString();
    }
}
