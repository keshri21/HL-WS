package com.hlws.dal;

import com.hlws.util.AppConstants;
import com.hlws.util.AppUtil;

public interface IBaseDAL {
	
	default String getSpecificCollectionName(String fixedCollectionName) {
		StringBuilder builder = new StringBuilder(fixedCollectionName);
		
		return builder.append(AppConstants.COLLECTION_DELIMETER)
				.append(AppUtil.getLoggedInUser().getCompanyId())
				.toString();
	}

}
