package com.hlws.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.hlws.dal.IBuiltyDAL;
import com.hlws.dal.IDoDAL;
import com.hlws.dal.IPanDAL;
import com.hlws.dal.IPermitDAL;
import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.Authority;
import com.hlws.enums.BiltyPaymentStatus;
import com.hlws.enums.BiltyUpdateType;
import com.hlws.helper.BillHelper;
import com.hlws.model.Builty;
import com.hlws.model.DO;
import com.hlws.model.Pan;
import com.hlws.model.Sequence;
import com.hlws.model.User;
import com.hlws.rest.resource.BuiltyResource;
import com.hlws.util.AppUtil;
import com.hlws.util.DateUtil;
import com.hlws.util.XlsUtil;

@Service
public class BuiltyService {

	private final Logger LOG = LoggerFactory.getLogger(BuiltyService.class);
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
		validateBilty(builty);
		DO doObj = doRepository.findById(builty.getDoId());
		if(doObj == null) {
			throw new Exception("Please select a valid DO");
		} else if(doObj.getDueDate().before(builty.getBuiltyDate())) {
			throw new Exception("Builty can't be created as DO due date is passed");
		}else if(doObj.getDoBalance() - builty.getNetWeight() < 0){	//check if DO have sufficient quantity left for new bilty
			throw new Exception("Builty can't be created as not sufficient balance left in this DO. Try with net weight so that DO balance doesn't go below 0");
		}
		synchronized (this) {
			Sequence currSeq = builtyRepository.getSequence(DateUtil.currYear());
			builty.setBuiltyNo(generateBuiltyNumber(currSeq.getValue()));
			//update builty sequence number before saving builty
			currSeq.setValue(currSeq.getValue()+1);

			builtyRepository.updateSequence(currSeq);
		}
		
		builty.setCreatedBy(AppUtil.getLoggedInUser().getUsername());
		builty.setCreatedByFullName(AppUtil.getLoggedInUser().getName());
		builty.setCreatedDateTime(new Date());
		builty.setPaymentStatus(BiltyPaymentStatus.CREATED.getStatusCode());
		builtyRepository.save(builty);
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(new BalanceUpdaterThread(builty, AppUtil.getLoggedInUser().getCompanyId()));
/*		//update do balance
		doRepository.updateDOBalance(builty.getDoId(), (-builty.getNetWeight()));
		// update permit balance
		if(null != builty.getPermitNo() && null != builty.getPermitBalance())
			permitRepository.updatePermitBalance(builty.getPermitNo(), (-builty.getNetWeight()));
		//remove from temp if present
		builtyRepository.removeFromTemp(builty);*/
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
	
	@Secured("ROLE_ADMIN")
	public void update(Builty builty) throws Exception {
		validateBilty(builty);
		if(StringUtils.isEmpty(builty.getBuiltyNo())) {
			throw new Exception("Builty couldn't be updated as there was no builty number associated");
		}
		//get original bilty to check any change in net weight
		Builty origBilty = builtyRepository.findById(builty.getId());
		
		builty.setLastModifiedBy(AppUtil.getLoggedInUser().getUsername());
		builty.setLastModifiedDateTime(new Date());
		builtyRepository.save(builty);
		
		//check if netweight has changed and accordingly update DO balance and permit balance
		double diff = origBilty.getNetWeight() - builty.getNetWeight();
		if(Math.abs(diff) > 0) {
			doRepository.updateDOBalance(builty.getDoId(), diff);
			// update permit balance
			if(null != builty.getPermitNo() && null != builty.getPermitBalance())
				permitRepository.updatePermitBalance(builty.getPermitNo(), diff);
		}
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
		builtyRepository.updateReceipt(builtyList, BiltyUpdateType.FREIGHT_CALCULATION);
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
			//Pan pan = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			Pan pan = panRepository.getByPanNo(builty.getVehicleOwnerPan());
			if(null != pan) {
				BuiltyDTO dto = new BuiltyDTO();
				dto.setBuiltyNo(builty.getBuiltyNo());
				dto.setFreightBill(builty.getFreightBill());
				dto.setReceivedDate(builty.getReceivedDate());
				dto.setReceivedQuantity(builty.getReceivedQuantity());
				dto.setBuiltyDate(builty.getBuiltyDate());
				dto.setVehicleNo(builty.getVehicleNo());
				dto.setVehicleOwner(pan.getPanHolderName());
				dto.setBankDtlsAvailable(CollectionUtils.isNotEmpty(pan.getAccounts()));
				dto.setPanNo(pan.getPanNo());
				dto.setId(builty.getBuiltyNo());
				dto.setExtraPayment(pan.getExtraPayment());
				builtiesForPayment.add(dto);
			}else {
				LOG.error("No PAN found for Bilty [Bilty No: {}, Vehicle: {}, Bilty Date: {}]", 
						builty.getBuiltyNo(), builty.getVehicleNo(), builty.getBuiltyDate());
			}
		});
		Collections.sort(builtiesForPayment);
		return builtiesForPayment;
	}
	
	public List<BuiltyDTO> getBuiltiesForInitiatedPayments() {
		List<Builty> builties = builtyRepository.getBuiltiesForInitiatedPayments();
		List<BuiltyDTO> builtiesForPayment = new ArrayList<>();
		builties.forEach(builty -> {
			//Pan pan = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			Pan pan = panRepository.getByPanNo(builty.getVehicleOwnerPan());
			if(null != pan) {
				BuiltyDTO dto = new BuiltyDTO();
				dto.setBuiltyNo(builty.getBuiltyNo());
				dto.setFreightBill(builty.getFreightBill());
				dto.setReceivedDate(builty.getReceivedDate());
				dto.setReceivedQuantity(builty.getReceivedQuantity());
				dto.setBuiltyDate(builty.getBuiltyDate());
				dto.setVehicleNo(builty.getVehicleNo());
				dto.setVehicleOwner(pan.getPanHolderName());
				dto.setBankDtlsAvailable(CollectionUtils.isNotEmpty(pan.getAccounts()));
				dto.setPanNo(pan.getPanNo());
				dto.setId(builty.getBuiltyNo());
				dto.setExtraPayment(pan.getExtraPayment());
				builtiesForPayment.add(dto);
			}else {
				LOG.error("No PAN found for Bilty [Bilty No: {}, Vehicle: {}, Bilty Date: {}]", 
						builty.getBuiltyNo(), builty.getVehicleNo(), builty.getBuiltyDate());
			}
		});
		Collections.sort(builtiesForPayment);
		return builtiesForPayment;
	}
	
	public void markForPaymentCompletion(List<BuiltyDTO> bilties) {
		builtyRepository.updateReceipt(bilties, BiltyUpdateType.PAYMENT_DONE);
	}
	
	@Transactional
	public void revertPaymentInstruction(List<BuiltyDTO> bilties) {
		
		
		if(CollectionUtils.isNotEmpty(bilties)) {
			List<String> ids = bilties.stream().map(BuiltyDTO::getBuiltyNo)
					.collect(Collectors.toList());
			List<Builty> origBilties = builtyRepository.getAllSelected(ids);
			//group by PAN
			Map<String, List<Builty>> groupedByPan = origBilties.stream()
					.collect(Collectors.groupingBy(Builty::getVehicleOwnerPan));
			groupedByPan.entrySet().stream().forEach(entry -> {
				Double totalByPan = entry.getValue().stream()
						.collect(Collectors.summingDouble(Builty::getFreightBill));
				
			});
		}
		
		builtyRepository.updateReceipt(bilties, BiltyUpdateType.REVERT_INSTRUCTION);
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
		builtyRepository.updateReceipt(builties, BiltyUpdateType.PAYMENT_INSTRUCTION);
		return cacheKey;
	}
	
	
	public ByteArrayInputStream getFromCache(Integer key) {
		return XlsUtil.getFromCache(key);
	}
	
	private void validateBilty(Builty bilty) throws Exception{
		if(StringUtils.isEmpty(bilty.getDoId())){
			throw new Exception("A DO must be associated with builty");
		}else if(StringUtils.isEmpty(bilty.getVehicleNo())) {
			throw new Exception("No vehicle associated with bilty");
		}else if(StringUtils.isEmpty(bilty.getVehicleOwnerPan())) {
			throw new Exception("Vehicle owner details not associated");
		}else if(bilty.getNetWeight() == null || bilty.getNetWeight() <= 0) {
			throw new Exception("Net weight can't be 0");
		}
	}
	
	private class BalanceUpdaterThread implements Runnable{
		Builty builty;
		String companyId; //needed to perform background tasks as authenticated user
		BalanceUpdaterThread(Builty builtyToUpdate, String companyId){
			this.builty = builtyToUpdate;
			this.companyId = companyId;
		}
		@Override
		public void run() {
			try {
				
				AppUtil.setAuthenticationForBackgroundTasks(companyId);
				LOG.warn("**********Task submitted to update DO and permit balance for bilty [{}] **************", builty.getBuiltyNo());
				//update do balance
				doRepository.updateDOBalance(builty.getDoId(), (-builty.getNetWeight()));
				
				// update permit balance
				if(null != builty.getPermitNo() && null != builty.getPermitBalance())
					permitRepository.updatePermitBalance(builty.getPermitNo(), (-builty.getNetWeight()));
				//remove from temp if present
				builtyRepository.removeFromTemp(builty);				
				
				AppUtil.clearAuthenticationForBackgroundTasks();
			}catch(Exception e) {
				e.printStackTrace();
				LOG.error("Error in updating balance after builty creation [{}]", e.getMessage());
			}
		}
		
	}
}


