package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.Customer;
import com.app.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;

	//1. Display Reg Page with Form Backing Object
	@RequestMapping("/reg")
	public String showReg(Model map) {
		//Form backing object
		map.addAttribute("customer", new Customer());
		return "Register";
	}

	//2. On click submit read form data and save into DB
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveData(
			@ModelAttribute Customer customer,
			Model map)
	{
		//insert into DB
		Integer id=service.saveCustomer(customer);
		map.addAttribute("message", "customer '"+id+"' created");
		//clear form backing object
		map.addAttribute("customer", new Customer());
		return "Register";
	}

	//3. Display all records in DB at UI
	@RequestMapping("/all")
	public String showAll(Model map) {
		//fetch data from DB
		List<Customer> cobs=service.getAllcustomers();
		//send data to UI
		map.addAttribute("list", cobs);
		return "Data";
	}

	//4. fetch data based on id and display
	@RequestMapping("/view/{id}")
	public String viewOne(
			@PathVariable Integer id,
			Model map) 
	{
		Customer c=service.getOneCustomer(id);
		map.addAttribute("ob", c);
		return "View";
	}

	//5. delete row based on Id
	@RequestMapping("/delete/{id}")
	public String deleteOne(
			@PathVariable Integer id,
			Model map) 
	{
		service.deleteCustomer(id);
		//fetch all new data
		List<Customer> cobs=service.getAllcustomers();
		//send data to UI
		map.addAttribute("list", cobs);
		//success message 
		map.addAttribute("message", "Customer '"+id+"' deleted");
		return "Data";
	}	
}