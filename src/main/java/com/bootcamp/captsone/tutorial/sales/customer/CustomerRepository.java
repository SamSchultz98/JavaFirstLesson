package com.bootcamp.captsone.tutorial.sales.customer;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {

	
	Optional<Customer> findByCode(String code);		//If you use the name of the column correctly, will automatically work by itself
		
	
	
	
	
	
	
	
	
	
	
	
}

