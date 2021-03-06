package com.hlws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableGlobalMethodSecurity(
		securedEnabled=true)
public class HLWSApplication{
	
//	@Autowired
//	CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HLWSApplication.class, args);
	}
	
	/*@Override
	public void run(String... args) {
		repository.deleteAll();
		
		List<String> hobbies = new ArrayList<>();
		hobbies.add("chess");
		hobbies.add("volleyball");
		
		repository.save(new Customer("Vikas", hobbies));
		repository.save(new Customer("Nansi", Arrays.asList("Gossip", "making freinds")));
		
		System.out.println("Find All");
		for(Customer cst : repository.findAll()) {
			System.out.println(cst);
		}
		
		System.out.println("get individual");
		System.out.println(repository.findByFirstName("Nansi"));
	}*/
}
