package com.bootcamp.captsone.tutorial.sales.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bootcamp.captsone.tutorial.sales.customer.Customer;
@Entity
@Table(name="Orders")
public class Order {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(length=50, nullable=false)
private String description = "New Order";  //Setting default is same as c#
@Column(columnDefinition="decimal(9,2) not null default 0")
private double total;
@Column(length=30,nullable=false)
private String status;

@ManyToOne(optional=false)
@JoinColumn(name="customerid", columnDefinition="int")
private Customer customer;				//This does the foreign key

public Order() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}


}
