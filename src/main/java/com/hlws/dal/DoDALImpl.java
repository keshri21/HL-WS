package com.hlws.dal;

import com.hlws.model.DO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

	@Override
	public void updateDOBalance(String id, Double balance) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().inc("doBalance", balance);
		mongoTemplate.updateFirst(query, update, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));		
	}

	@Override
	public Boolean checkDoByBspDo(Long bspDoNo) {
		Query query = new Query().addCriteria(Criteria.where("bspDoNo").is(bspDoNo));
		List<DO> list = mongoTemplate.find(query, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return (null != list && list.size() > 0)  ? true : false;
	}

	@Override
	public void markComplete(List<String> doIds) {
		Query query = new Query().addCriteria(Criteria.where("_id").in(doIds));
		Update update = new Update().set("finishDate", new Date());
		mongoTemplate.updateMulti(query, update, DO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
    
	
    
}
