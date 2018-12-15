package com.hlws.dal;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.dto.BuiltyDTO;
import com.hlws.model.Builty;
import com.hlws.model.Sequence;
import com.mongodb.client.result.UpdateResult;

@Repository
public class BuiltyDALImpl implements IBuiltyDAL {

    private final MongoTemplate mongoTemplate;
    private static final String FIXED_COLLECTION_NAME = "builty";
    private static final String SEQUENCE_COLLECTION_NAME = "builty-sequence";
    
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
        Query query = new Query().addCriteria(Criteria.where("receivedDate").is(null).and("deleted").is(null));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("receivedDate").ne(null));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> getAll() {
    	Query query = new Query().addCriteria(Criteria.where("deleted").is(null));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
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
	
	@Override
	@CachePut("builtySequence")
	public Sequence updateSequence(Sequence sq) {
		/*if(sq.getValue() == this.getSequence(sq.getYear()).getValue()) {
			sq.setValue(sq.getValue()+1);
		}*/
		Query query = new Query().addCriteria(Criteria.where("year").is(sq.getYear()));
		Update update = new Update().set("value", sq.getValue());
		mongoTemplate.updateFirst(query, update, Sequence.class, getSpecificCollectionName(SEQUENCE_COLLECTION_NAME));
		return sq;
	}
	
	@Override
	@Cacheable("builtySequence")
	public Sequence getSequence(int year) {
		Query query = new Query().addCriteria(Criteria.where("year").is(year));
		Sequence seq = mongoTemplate.findOne(query, Sequence.class, getSpecificCollectionName(SEQUENCE_COLLECTION_NAME));

		if(null == seq) {
			seq = new Sequence();
			seq.setValue(1);
			seq.setYear(year);
			mongoTemplate.save(seq, getSpecificCollectionName(SEQUENCE_COLLECTION_NAME));
		}
		return seq;
	}

	@Override
	public Builty findBuiltyByVehicleNo(String vehicleno) {
		Query query = new Query(Criteria.where("vehicleNo").is(vehicleno));
		return mongoTemplate.findOne(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public boolean delete(String builtyId) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(builtyId));
		Update update = new Update();
		update.set("deleted", true);
		UpdateResult result = mongoTemplate.updateFirst(query, update, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));

		return result.wasAcknowledged();
	}

	@Override
	public List<Builty> getByUserAndCurrentDate(String username) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		Query query = new Query().addCriteria(Criteria.where("createdBy").is(username).and("createdDateTime").gt(cal.getTime()));
		return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
	
	
	
    
    
}
