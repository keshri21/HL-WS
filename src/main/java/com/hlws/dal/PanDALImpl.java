package com.hlws.dal;

import com.hlws.model.Pan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PanDALImpl implements IPanDAL{

    private final MongoTemplate mongoTemplate;
    private String collectionName = "pan";

    @Autowired
    public PanDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Pan save(Pan pan) {
        mongoTemplate.save(pan, collectionName);
        return pan;
    }

    @Override
    public List<Pan> getAll() {
        return mongoTemplate.findAll(Pan.class, collectionName);
    }

    @Override
    public List<Pan> findBySearchText(String searchText) {
        Query query = new Query().addCriteria(Criteria.where("panNo").regex(searchText));
        return mongoTemplate.find(query, Pan.class, collectionName);
    }

    @Override
    public Pan getOne(String panNo) {
        Query query = new Query().addCriteria(Criteria.where("panNo").is(panNo));
        return mongoTemplate.findOne(query, Pan.class, collectionName);
    }
}
