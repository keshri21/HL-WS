package com.hlws.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hlws.dal.IBuiltyDAL;
import com.hlws.dal.IDoDAL;
import com.hlws.dal.IPanDAL;
import com.hlws.dal.IPermitDAL;
import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.Authority;
import com.hlws.helper.BillHelper;
import com.hlws.model.Builty;
import com.hlws.model.Pan;
import com.hlws.model.Sequence;
import com.hlws.model.User;
import com.hlws.util.AppUtil;
import com.hlws.util.DateUtil;

@Service
public class BuiltyService {

	@Autowired
	IBuiltyDAL builtyRepository;
	
	@Autowired
	IDoDAL doRepository;
	
	@Autowired
	IPermitDAL permitRepository;
	
	@Autowired
	IPanDAL panRepository;
	
	@Autowired
	private BillHelper billHelper;

	public Builty createBuilty(Builty builty) throws Exception{
		if(StringUtils.isEmpty(builty.getDoId())){
			throw new Exception("A DO must be associated with builty");
		}
		synchronized (this) {
			Sequence currSeq = builtyRepository.getSequence(DateUtil.currYear());
			builty.setBuiltyNo(generateBuiltyNumber(currSeq.getValue()));
			//update builty sequence number before saving builty
			currSeq.setValue(currSeq.getValue()+1);

			builtyRepository.updateSequence(currSeq);
		}
		
		builty.setCreatedBy(AppUtil.getLoggedInUser().getUsername());
		builty.setCreatedDateTime(new Date());
		builtyRepository.save(builty);
		
		// submit a runable to update do and permit balance TODO find a way to get logged in user even response is returned
//		ExecutorService service = Executors.newSingleThreadExecutor();
//		service.execute(new BalanceUpdaterThread(builty));
		//update do balance
		doRepository.updateDOBalance(builty.getDoId(), (-builty.getNetWeight()));
		// update permit balance
		if(null != builty.getPermitNo() && null != builty.getPermitBalance())
			permitRepository.updatePermitBalance(builty.getPermitNo(), (-builty.getNetWeight()));
		//remove from temp if present
		builtyRepository.removeFromTemp(builty);
		return builty;
	}

	public Builty saveBuilty(Builty builty) throws Exception{
		if(builty.getSavedReferenceNumber() > -1) {
			Long random = new Random().nextLong();
			builty.setSavedReferenceNumber(random);
		}
		builtyRepository.saveTemp(builty);
		return builty;
	}
	
	public List<Builty> getBuiltyList(String filterParam) throws Exception{
		List<Builty> list = new ArrayList<>();
		switch(filterParam) {
			case "all": list = builtyRepository.getAll();
						break;
			case "completed": 	list = builtyRepository.findCompleted();
								break; 
			default:	User user = AppUtil.getLoggedInUser();
				if(Authority.ROLE_ADMIN.name().equals(user.getRoleName())){
					list = builtyRepository.findRunning();
				}else {
					list = builtyRepository.getByUserAndCurrentDate(user.getUsername());
				}
				 
		}
		Collections.sort(list);
		return list;
	}
	public void update(Builty builty) throws Exception {
		if(StringUtils.isEmpty(builty.getDoId())){
			throw new Exception("A DO must be associated with builty");
		}else if(StringUtils.isEmpty(builty.getBuiltyNo())) {
			throw new Exception("Builty couldn't be updated as there was no builty number associated");
		}
		builty.setLastModifiedBy(AppUtil.getLoggedInUser().getUsername());
		builty.setLastModifiedDateTime(new Date());
		builtyRepository.save(builty);
	}
	
	public boolean delete(String builtyId) throws Exception {
		boolean result = builtyRepository.delete(builtyId); 
		if(result) {
			Builty builty = builtyRepository.findById(builtyId);
			Double netweight = builty.getNetWeight();
			doRepository.updateDOBalance(builty.getDoId(), netweight);
			if(null != builty.getPermitNo() && builty.getPermitNo() > 0) {
				//update permit balance as builty is deleted
				permitRepository.updatePermitBalance(builty.getPermitNo(), netweight);
			}
		}
		if(!result) {
			throw new Exception("Builty couldn't be deleted");
		}
		return result;
	}
	public Builty getOne(String id) {
		return builtyRepository.findById(id);
	}
	
	public void approve(String id) {
		builtyRepository.approve(id);
	}
	
	public void updateReceipt(List<BuiltyDTO> builtyList) throws Exception{
		builtyRepository.updateReceipt(builtyList, false);
	}
	
	public void resetInstruction(String builtyNo) {
		builtyRepository.resetPaymentInstruction(builtyNo);
	}
	
	public List<Builty> getAllFromTemp() {
		return builtyRepository.getAllFromTemp();
	}
	
	public Builty getOneFromTemp(String id) {
		return builtyRepository.getOneFromTemp(id);
	}
	
	public List<Builty> getAllSelected(List<String> ids){
		return builtyRepository.getAllSelected(ids);
	}
	
	public List<BuiltyDTO> getBuiltiesForPayments() {
		List<Builty> builties = builtyRepository.getBuiltiesForPayments();
		List<BuiltyDTO> builtiesForPayment = new ArrayList<>();
		builties.forEach(builty -> {
			Pan pan = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			BuiltyDTO dto = new BuiltyDTO();
			dto.setBuiltyNo(builty.getBuiltyNo());
			dto.setFreightBill(builty.getFreightBill());
			dto.setReceivedDate(builty.getReceivedDate());
			dto.setReceivedQuantity(builty.getReceivedQuantity());
			dto.setBuiltyDate(builty.getBuiltyDate());
			dto.setVehicleNo(builty.getVehicleNo());
			dto.setVehicleOwner(pan.getPanHolderName());
			dto.setBankDtlsAvailable(CollectionUtils.isNotEmpty(pan.getAccounts()));
			builtiesForPayment.add(dto);
		});
		Collections.sort(builtiesForPayment);
		return builtiesForPayment;
	}
	
	private String generateBuiltyNumber(Integer seqNo) {
		StringBuilder builder = new StringBuilder();
		builder.append(AppUtil.getLoggedInUser().getCompanyId())
				.append(Calendar.getInstance().get(Calendar.YEAR))
				.append(String.format("%04d", seqNo));
		return builder.toString().toUpperCase();
	}
	
	public Integer getInstructions(List<BuiltyDTO> builties) throws IOException{
		Integer cacheKey = billHelper.generatePaymentInstructionSheet(builties);
		//mark paymentInstructionDone true
		builtyRepository.updateReceipt(builties, true);
		return cacheKey;
	}
	
	
	public ByteArrayInputStream getFromCache(Integer key) {
		return billHelper.getInstructions(key);
	}
	private class BalanceUpdaterThread implements Runnable{
		
		private Builty builty;

		public BalanceUpdaterThread(Builty builty) {
			super();
			this.builty = builty;
		}

		@Override
		public void run() {
			try {
				//update do balance
				doRepository.updateDOBalance(builty.getDoId(), builty.getDoClosingBalance());
				// update permit balance
				if(null != builty.getPermitNo())
					permitRepository.updatePermitBalance(builty.getPermitNo(), builty.getPermitBalance());
				//remove if temporary builty was created for this builty
				builtyRepository.removeFromTemp(builty);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}


