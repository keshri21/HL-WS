package com.hlws.dal;

import com.hlws.model.Pan;
import com.hlws.model.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class PanDALImpl implements IPanDAL{

    private final MongoTemplate mongoTemplate;
    private static final String FIXED_COLLECTION_NAME = "pan";

    @Autowired
    public PanDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Pan save(Pan pan) {
        mongoTemplate.save(pan, FIXED_COLLECTION_NAME);
        return pan;
    }

    @Override
    public List<Pan> getAll() {
        return mongoTemplate.findAll(Pan.class, FIXED_COLLECTION_NAME);
    }

    @Override
    public List<Pan> findBySearchText(String searchText) {
        Query query = new Query().addCriteria(Criteria.where("panNo").regex(searchText, "i"));
        return mongoTemplate.find(query, Pan.class, FIXED_COLLECTION_NAME);
    }

    @Override
    public Pan getOne(String panNo) {
        Query query = new Query().addCriteria(Criteria.where("panNo").is(panNo));
        return mongoTemplate.findOne(query, Pan.class, FIXED_COLLECTION_NAME);
    }

	@Override
	public void updateVehicles(String pan, Set<Vehicle> updatedList) {
		Query query = new Query().addCriteria(Criteria.where("panNo").is(pan));
		Update update = new Update().set("vehicles", updatedList);
		mongoTemplate.updateFirst(query, update, FIXED_COLLECTION_NAME);
	}

	@Override
	public List<Pan> getVehicles(String searchText) {
		Query query = new Query().addCriteria(Criteria.where("vehicles.vehicleNo").regex(searchText, "i"));
        return mongoTemplate.find(query, Pan.class, FIXED_COLLECTION_NAME);
	}
	
	@Override
    public void updateVehicleOwner(String vehicleNo) {
    	Query query = new Query().addCriteria(Criteria.where("vehicles.vehicleNo").is(vehicleNo)
        		.and("vehicles.isOldOwner").is(false));
        Update update = new Update().set("vehicles.isOldOwner", true);
        mongoTemplate.updateMulti(query, update, FIXED_COLLECTION_NAME);
    }
	
    
    
}
