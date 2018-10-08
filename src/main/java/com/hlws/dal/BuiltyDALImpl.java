package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.dto.BuiltyDTO;
import com.hlws.exceptions.DALInitiationException;
import com.hlws.model.Builty;

@Repository
public class BuiltyDALImpl implements IBuiltyDAL {

    private final MongoTemplate mongoTemplate;
    private static final String FIXED_COLLECTION_NAME = "builty";

    @Autowired
    public BuiltyDALImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Builty save(Builty builty) {
        mongoTemplate.save(builty, getSpecificCollectionName(FIXED_COLLECTION_NAME));
        return builty;
    }

    @Override
    public List<Builty> findRunning() {
        Query query = new Query().addCriteria(Criteria.where("receivedDate").is(null));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("receivedDate").ne(null));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> getAll() {
        return mongoTemplate.findAll(Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public Builty findById(String id) {
        return mongoTemplate.findById(id, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public Builty saveTemp(Builty builty) {
        mongoTemplate.save(builty, getTempCollectionName(FIXED_COLLECTION_NAME));
        return builty;
    }

    @Override
    public List<Builty> getAllFromTemp() {
        return mongoTemplate.findAll(Builty.class, getTempCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public Builty getOneFromTemp(String id) {
        return mongoTemplate.findById(id, Builty.class, getTempCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public void removeFromTemp(Builty builty) {
        /*Query query = new Query().addCriteria(Criteria.where("savedReferenceNumber")
                .is(builty.getSavedReferenceNumber()));*/
        mongoTemplate.remove(builty, getTempCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> getAllSelected(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }
    

	@Override
	public List<Builty> getAllForDO(List<String> doIds) {
		Query query = new Query(Criteria.where("doId").in(doIds));
		return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public void updateReceipt(List<BuiltyDTO> builtyList) {
		builtyList.forEach(builty -> {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(builty.getId()));
			Update update = new Update().set("receivedDate", builty.getReceivedDate())
					.set("receivedQuantity", builty.getReceivedQuantity());
			mongoTemplate.updateFirst(query, update, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		});
		
	}

	@Override
	public void approve(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update().set("approved", true);
		mongoTemplate.updateFirst(query, update, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
	
	
    
    
}
