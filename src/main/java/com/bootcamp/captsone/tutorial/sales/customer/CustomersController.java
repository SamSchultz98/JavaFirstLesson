package com.bootcamp.captsone.tutorial.sales.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController 		//Send and receive .json
@RequestMapping("/api/customers")					//Every method in this controller will have this part of the url
public class CustomersController {
	
	@Autowired		//Effectively creates a constructor who takes in the instance of the constructor below. The same as the DB context in c#
	private CustomerRepository custRepo;		//Fills in custRepo with the instance of Customer Repository
	
	
	
	@GetMapping				//Iterable is for getting back multiple, or a list of
	public ResponseEntity<Iterable<Customer>> getCustomers(){
		Iterable<Customer> customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
		
	}
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomerByPk(@PathVariable int id){
		Optional<Customer> customer = custRepo.findById(id);
		if (customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
		if(customer.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		//Dont forget to add <> in the ResponseEntity since it returns no data
		}
		Customer newcustomer = custRepo.save(customer);
		return new ResponseEntity<Customer>(newcustomer, HttpStatus.CREATED);
		
		
	}
	@PutMapping("{id}")
	public ResponseEntity putCustomer(@PathVariable int id, @RequestBody Customer customer) {
		if(id != customer.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Customer> cust = custRepo.findById(id);		//If it can be null, must put optional<>
		if(cust.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		Optional<Customer> customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.delete(customer.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("code/code")
	public ResponseEntity<Customer> getCustomerByCode(@PathVariable String code){
		Optional<Customer> customer = custRepo.findByCode(code);
		if (customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		
		
	}

}
