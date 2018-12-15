package com.hlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.ICompanyDAL;
import com.hlws.model.Company;

@Service
public class CompanyService {

	@Autowired
	ICompanyDAL companyRepository;
	
	public Company create(Company company) {
		company.setActive(true);
		companyRepository.save(company);
		return company;
	}
	
	public boolean delete(String companyId) {
		companyRepository.delete(companyId);
		return true;
	}
	
	public List<Company> getAll(){
		return companyRepository.getCompanies();
	}
}
