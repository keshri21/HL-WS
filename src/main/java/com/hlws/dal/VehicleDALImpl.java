package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.model.Vehicle;

@Repository
public class VehicleDALImpl implements IVehicleDAL {

    private final MongoTemplate mongoTemplate;
    private static final String FIXED_COLLECTION_NAME = "pan";

    @Autowired
    public VehicleDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        mongoTemplate.save(vehicle);
        return vehicle;
    }

    /*@Override
    public Vehicle insert(Vehicle vehicle) {
        return mongoTemplate.insert();
    }*/

    @Override
    public List<Vehicle> findBySearchText(String searchText) {
        Query query = new Query().addCriteria(Criteria.where("vehicleNo").regex(searchText)
        		.and("isOldOwner").is(false));

        return mongoTemplate.find(query, Vehicle.class, FIXED_COLLECTION_NAME);
    }

    @Override
    public void updateOwner(String vehicleNo) {
    	Query query = new Query().addCriteria(Criteria.where("vehicleNo").is(vehicleNo)
        		.and("isOldOwner").is(false));
        Update update = new Update().set("isOldOwner", true);
        mongoTemplate.updateMulti(query, update, FIXED_COLLECTION_NAME);
    }
}


