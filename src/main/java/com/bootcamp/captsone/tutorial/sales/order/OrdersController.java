package com.bootcamp.captsone.tutorial.sales.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	@Autowired		//The constructor is being taken care of for us (Like the dbcontext in c#)
	OrderRepository orderRepo;

	
	@GetMapping
	public ResponseEntity<Iterable<Order>> getOrders() {
		Iterable<Order> orders = orderRepo.findAll();
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
		
	}
	@GetMapping("{id}")
	public ResponseEntity<Order> getOrderByPk(@PathVariable int id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);		//Must include HTTPStatus
		
		
	}
	
	@PostMapping
	public ResponseEntity<Order> postOrder(@RequestBody Order order){
		if (order.getId() != 0) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		order.setStatus("New");		//This makes the new order status set to "NEW"
		Order newOrder = orderRepo.save(order);
		return new ResponseEntity<>(newOrder,HttpStatus.NO_CONTENT);	
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Order> putOrder(@PathVariable int id, @RequestBody Order order){
		if (id != order.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Optional<Order> orderT = orderRepo.findById(id);
		if(orderT.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		orderRepo.save(order);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteOrder(@PathVariable int id) {
		var ord = orderRepo.findById(id);
		if(ord.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		orderRepo.delete(ord.get());
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	
	
	
	
	

}
