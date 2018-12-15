package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.model.Company;

@Repository
public class CompanyDALImpl implements ICompanyDAL {

	private final MongoTemplate mongoTemplate;
	
	//private static final String COMPANY_COLLECTION = "company";
	
	@Autowired
	public CompanyDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	@Cacheable("companies")
	public List<Company> getCompanies() {
		Query query = new Query().addCriteria(Criteria.where("active").is(true));
		return mongoTemplate.find(query, Company.class);
	}

	@Override
	@CacheEvict("companies")
	public void save(Company company) {
		mongoTemplate.save(company);
	}

	@Override
	@CacheEvict("companies")
	public void delete(String companyId) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(companyId));
		Update update = new Update().set("active", false);
		mongoTemplate.updateFirst(query, update, Company.class);		
	}
	
	

}
