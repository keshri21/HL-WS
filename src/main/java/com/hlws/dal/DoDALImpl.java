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
    private static final String FIXED_COLLECTION_NAME = "do";

    @Autowired
    public DoDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public DO save(DO doObj) {
        mongoTemplate.save(doObj, getSpecificCollectionName(FIXED_COLLECTION_NAME));
        return doObj;
    }

    @Override
    public List<DO> findRunning() {
        Query query = new Query().addCriteria(Criteria.where("finishDate").is(null));
        return mongoTemplate.find(query, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<DO> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("finishDate").ne(null));
        return mongoTemplate.find(query, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<DO> getAll() {
        return mongoTemplate.findAll(DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public DO findById(String id) {
        return mongoTemplate.findById(id, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public void delete(DO doObj) {
        mongoTemplate.remove(doObj, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<DO> getAllSelected(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }
}
