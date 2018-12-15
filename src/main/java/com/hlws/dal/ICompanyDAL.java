package com.hlws.dal;

import java.util.List;

import com.hlws.model.Company;

public interface ICompanyDAL extends IBaseDAL {
	List<Company> getCompanies();
	void save(Company company);
	void delete(String companyId);
}
