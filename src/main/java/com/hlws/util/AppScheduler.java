package com.hlws.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hlws.dal.ICompanyDAL;
import com.hlws.dal.IDoDAL;
import com.hlws.enums.Authority;
import com.hlws.model.Company;
import com.hlws.model.DO;
import com.hlws.model.User;

@Component
public class AppScheduler {
	
	@Autowired
	private IDoDAL doRepository;
	
	@Autowired
	private ICompanyDAL companyRepository;
	
	// run this job evry day at 11PM
	@Scheduled(cron="0 0 23 * * *")
	public void runTask() {
		
		List<Company> companies = companyRepository.getCompanies();
		for(Company company : companies) {
			
			//set authentcation on securitycontext as this is schduled task and authentication will not be available
			// where company id is used
			List<Authority> roles = Arrays.asList(Authority.ROLE_ADMIN);
			User user = new User();
			user.setAuthorities(roles);
			user.setCompanyId(company.getUniqueShortName());
			AuthenticationUtil.configureAuthentication(user);
			
			List<DO> runningDO = doRepository.findRunning();
			Calendar cal = Calendar.getInstance();
			Calendar currCal = Calendar.getInstance();
			//runningDO.stream().filter(currDO -> )
			List<String> doIdToMarkComplete = new ArrayList<>();
			for(DO doObj : runningDO) {
				cal.setTime(doObj.getDueDate());
				//cal.add(Calendar.DATE, 45);
				if(cal.before(currCal)) {
					doIdToMarkComplete.add(doObj.getId());
				}
			}
			
			//mark complete
			doRepository.markComplete(doIdToMarkComplete);
			
			// clear authentication when job is done
			AuthenticationUtil.clearAuthentication();
		}
	}
}
