package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.repo.CustomerRepository;
import com.app.service.ICustomerService;
@Service
public class CustomerService implements ICustomerService{


	@Autowired
	private CustomerRepository repo;

	@Transactional
	public Integer saveCustomer(Customer c) {
		/*Custmer c=repo.save(c);
		 Integer id=c.getCustId(c);
		 return id:*/
		return repo.save(c).getCustId();

	}

	@Transactional(readOnly= true)
	public List<Customer> getAllcustomers() {

		return repo.findAll();
	}
	@Cacheable(value="emp-cache",key="#empId")

	@Transactional(readOnly = true)
	public Customer getOneCustomer(Integer custId) {
		Optional<Customer> c= repo.findById(custId);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}
	@CacheEvict(value="emp-cache",key="#empId")
	@Transactional
	public void deleteCustomer(Integer custId) {
		repo.deleteById(custId);

	}

	@Override
	public void UpdateCustomer(Integer Id, Customer c) {
		// TODO Auto-generated method stub
		repo.save(c);
	}

}
