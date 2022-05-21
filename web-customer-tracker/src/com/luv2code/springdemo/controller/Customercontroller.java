package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class Customercontroller {
	
	//need to inject customer DAO
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String customerList(Model themodel) {
		
		//get the customers from DAO
		List<Customer> theCustomer = customerService.getCustomer();
		
		//Add the customer to model
		themodel.addAttribute("customers",theCustomer);
		
		return "customer-list";
		
		}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer thecustomer = new Customer();
		theModel.addAttribute("customer",thecustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showUpdateCustomer")
	public String showUpdateCustomer(@RequestParam("customerId") int theId, Model theModel){
		
		//get data from customer
		Customer theCustomer = customerService.getCustomer(theId);
		//add it to model
		theModel.addAttribute("customer",theCustomer);
		//return it to view page
		return "customer-form";
	}
	
	@GetMapping("/showDeleteCustomer")
	public String showDeleteCustomer(@RequestParam("customerId") int theId){
		
		//get data from customer
       Customer theCustomer = customerService.getCustomer(theId);
       
       customerService.deleteCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	

}
