package com.hlws.dal;

import com.hlws.model.DO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoDALImpl implements IDoDAL {

    private final MongoTemplate mongoTemplate;
    private String collectionName = "hl-do";

    @Autowired
    public DoDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public DO save(DO doObj) {
        mongoTemplate.save(doObj, collectionName);
        return doObj;
    }

    @Override
    public List<DO> findRunning() {
        Query query = new Query().addCriteria(Criteria.where("finishDate").is(null));
        return mongoTemplate.find(query, DO.class, collectionName);
    }

    @Override
    public List<DO> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("finishDate").ne(null));
        return mongoTemplate.find(query, DO.class, collectionName);
    }

    @Override
    public List<DO> getAll() {
        return mongoTemplate.findAll(DO.class, collectionName);
    }

    @Override
    public DO findById(String id) {
        return mongoTemplate.findById(id, DO.class, collectionName);
    }

    @Override
    public void delete(DO doObj) {
        mongoTemplate.remove(doObj, collectionName);
    }

    @Override
    public List<DO> getAllSelected(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, DO.class, collectionName);
    }
}
