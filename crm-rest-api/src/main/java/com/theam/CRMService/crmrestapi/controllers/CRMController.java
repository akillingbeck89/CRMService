package com.theam.CRMService.crmrestapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.IResponse;
import com.theam.CRMService.crmrestapi.models.QuickResponse;

@Component
public class CRMController {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private CustomerDaoService CustomerService;

	//Need to be able to mutate and get data for
	/*
	 * Users
	 * Customers
	 * 
	 * */
	
	public IResponse GetCustomers(Integer startIndex,Integer limit) {
		
		return CustomerService.GetCustomers(startIndex,limit);
	}
	
	public IResponse GetCustomerDetails(Integer customerID) {
		return CustomerService.GetCustomerDetails(customerID);
	}
	
	public IResponse CreateCustomer(String name, String surname,String email) {
		
		return CustomerService.CreateCustomer(name, surname,email);
	}
	
	public IResponse DeleteCustomer(Integer customerID) {
		return CustomerService.DeleteCustomer(customerID);
	}
	public IResponse UpdateCustomer(Integer customerID, String name,String surname) {
		return CustomerService.UpdateCustomer(customerID, name, surname);
	}
	
	public IResponse UploadCustomerPhoto(Integer customerID, MultipartFile file) {
		
		return CustomerService.UpdateCustomerPhoto(customerID, file);
	}
	
	
	public IResponse CreateUser(String userID, String details) {
		return new QuickResponse("Created user " + userID + " With " + details);
	}
	
	public IResponse DeleteUser(String userID) {
		return new QuickResponse("Deleting user " + userID);
	}
	
	public IResponse UpdateUser(String userID,String details) {
		return new QuickResponse("Updating user " + userID + " With " + details);
	}
	public IResponse GetUsers() {
		return new QuickResponse("Getting users");
	}
	
	public IResponse PromoteUser(String userID,int to) {
		return new QuickResponse("Promoting user " + userID + "to: " + to);
	}
	public IResponse DemoteUser(String userID,int to) {
		return new QuickResponse("Demoting user " + userID + "to: " + to);
	}
}
