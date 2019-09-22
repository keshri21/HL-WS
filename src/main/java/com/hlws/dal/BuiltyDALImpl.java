package com.hlws.dal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.BiltyPaymentStatus;
import com.hlws.enums.BiltyUpdateType;
import com.hlws.model.Builty;
import com.hlws.model.Sequence;
import com.hlws.rest.resource.BuiltyResource;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class BuiltyDALImpl implements IBuiltyDAL {

	private final Logger LOG = LoggerFactory.getLogger(BuiltyDALImpl.class);
	
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
        Query query = new Query().addCriteria(Criteria.where("paymentStatus")
        		.in(BiltyPaymentStatus.READY_FOR_PAYMENT.getStatusCode(), BiltyPaymentStatus.CREATED.getStatusCode()));
        return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
    }

    @Override
    public List<Builty> findCompleted() {
        Query query = new Query().addCriteria(Criteria.where("paymentStatus").is(BiltyPaymentStatus.DONE.getStatusCode()));
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
	public void updateReceipt(List<BuiltyDTO> builtyList, BiltyUpdateType updateType) {
		builtyList.forEach(builty -> {
			Query query = new Query();
			Update update = new Update();
			switch(updateType) {
				case FREIGHT_CALCULATION:
					query.addCriteria(Criteria.where("_id").is(builty.getId()));
					update.set("receivedDate", builty.getReceivedDate())
							.set("receivedQuantity", builty.getReceivedQuantity())
							.set("freightBill", builty.getFreightBill())
							.set("paymentStatus", BiltyPaymentStatus.READY_FOR_PAYMENT.getStatusCode());
					break;
				case PAYMENT_INSTRUCTION:
					query.addCriteria(Criteria.where("builtyNo").is(builty.getBuiltyNo()));
					update.set("paymentStatus", BiltyPaymentStatus.INITIATED.getStatusCode())
							.set("paymentInstructionDateTime", new Date());
					break;
				case REVERT_INSTRUCTION:
					query.addCriteria(Criteria.where("builtyNo").is(builty.getBuiltyNo()));
					update.set("paymentStatus", BiltyPaymentStatus.READY_FOR_PAYMENT.getStatusCode())
							.set("paymentInstructionDateTime", new Date());
					break;
				case PAYMENT_DONE:
					query.addCriteria(Criteria.where("builtyNo").is(builty.getBuiltyNo()));
					update.set("paymentStatus", BiltyPaymentStatus.DONE.getStatusCode());
					break;
				default:
					LOG.error("Invalid Update bilty request received. Bilty No. [{}]", builty.getBuiltyNo());
			}
			
			mongoTemplate.updateFirst(query, update, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		});
		
	}
	
	

	@Override
	public void resetPaymentInstruction(String builtyNo) {
		Query query = new Query();
		Update update = new Update();
		query.addCriteria(Criteria.where("builtyNo").is(builtyNo));
		update.set("paymentStatus", BiltyPaymentStatus.READY_FOR_PAYMENT.getStatusCode());
		mongoTemplate.updateFirst(query, update, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
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
		DeleteResult result = mongoTemplate.remove(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));

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

	@Override
	public List<Builty> getBuiltiesForPayments() {
		Query query = new Query(Criteria.where("paymentStatus").is(BiltyPaymentStatus.READY_FOR_PAYMENT.getStatusCode()));
		return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
	
	@Override
	public List<Builty> getBuiltiesForInitiatedPayments() {
		Query query = new Query(Criteria.where("paymentStatus").is(BiltyPaymentStatus.INITIATED.getStatusCode()));
		return mongoTemplate.find(query, Builty.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
	
}
