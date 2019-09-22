package com.hlws.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hlws.dal.ICompanyDAL;
import com.hlws.dal.IDoDAL;
import com.hlws.model.Company;
import com.hlws.model.DO;

@Component
public class AppScheduler {
	
	private final Logger LOG = LoggerFactory.getLogger(AppScheduler.class);
	
	@Autowired
	private IDoDAL doRepository;
	
	@Autowired
	private ICompanyDAL companyRepository;
	
	// run this job evry day at 11PM
	@Scheduled(cron="0 0 23 * * *")
	public void runTask() {
		LOG.warn("*************Starting scheduler to check for completed DO*************");
		try {
			List<Company> companies = companyRepository.getCompanies();
			for(Company company : companies) {
				//set authentication to run queries
				AppUtil.setAuthenticationForBackgroundTasks(company.getUniqueShortName());
				
				List<DO> runningDO = doRepository.findRunning();
				Calendar cal = Calendar.getInstance();
				Calendar currCal = Calendar.getInstance();
				List<String> doIdToMarkComplete = new ArrayList<>();
				for(DO doObj : runningDO) {
					cal.setTime(doObj.getDueDate());
					//cal.add(Calendar.DATE, 45);
					if(cal.before(currCal) || doObj.getDoBalance() <= 0) {
						doIdToMarkComplete.add(doObj.getId());
					}
				}			
				LOG.info("DO identified to be marked completed for company [{}] are {}", company.getCompanyName(), doIdToMarkComplete);
				//mark complete
				doRepository.markComplete(doIdToMarkComplete);
				
				// clear authentication when job is done
				AppUtil.clearAuthenticationForBackgroundTasks();
			}
		}catch (Exception e) {
			LOG.error("Scheduler failed while checking for completed DO [{}]", e.getMessage());
			e.printStackTrace();
		}
	}
}
