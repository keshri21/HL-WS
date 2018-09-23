package com.hlws.dal;

import com.hlws.model.Builty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuiltyDALImpl implements IBuiltyDAL {

    private final MongoTemplate mongoTemplate;
    private String collectionName = "hl-builty";
    private String tempCollectionName = collectionName + "-temp";

    @Autowired
    public BuiltyDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Builty save(Builty builty) {
        mongoTemplate.save(builty, collectionName);
        return builty;
    }

    @Override
    public List<Builty> findRunning() {
        Query query = new Query().addCriteria(Criteria.where("receivedDate").is(null));
        return mongoTemplate.find(query, Builty.class, collectionName);
    }

    @Override
    public List<Builty> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("receivedDate").ne(null));
        return mongoTemplate.find(query, Builty.class, collectionName);
    }

    @Override
    public List<Builty> getAll() {
        return mongoTemplate.findAll(Builty.class, collectionName);
    }

    @Override
    public Builty findById(String id) {
        return mongoTemplate.findById(id, Builty.class, collectionName);
    }

    @Override
    public Builty saveTemp(Builty builty) {
        mongoTemplate.save(builty, tempCollectionName);
        return builty;
    }

    @Override
    public List<Builty> getAllFromTemp() {
        return mongoTemplate.findAll(Builty.class, tempCollectionName);
    }

    @Override
    public Builty getOneFromTemp(String id) {
        return mongoTemplate.findById(id, Builty.class, tempCollectionName);
    }

    @Override
    public void removeFromTemp(Builty builty) {
        /*Query query = new Query().addCriteria(Criteria.where("savedReferenceNumber")
                .is(builty.getSavedReferenceNumber()));*/
        mongoTemplate.remove(builty, tempCollectionName);
    }

    @Override
    public List<Builty> getAllSelected(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, Builty.class, collectionName);
    }
}
