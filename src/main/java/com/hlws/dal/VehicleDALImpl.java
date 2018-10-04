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

@Repository
public class VehicleDALImpl implements IVehicleDAL {

    private final MongoTemplate mongoTemplate;
    private String collectionName = "pan";

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

        return mongoTemplate.find(query, Vehicle.class, collectionName);
    }

    @Override
    public void updateOwner(String vehicleNo) {
        Update update = new Update(); //TODO complete this method
    }
}


