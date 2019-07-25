package com.app.service;

import java.util.List;

import org.hibernate.sql.Update;

import com.app.model.Customer;

public interface ICustomerService {

	public Integer saveCustomer(Customer c);
	public List<Customer> getAllcustomers();
	public Customer getOneCustomer(Integer custId);
	public void deleteCustomer(Integer custId);
	public void UpdateCustomer(Integer Id, Customer c);
}
