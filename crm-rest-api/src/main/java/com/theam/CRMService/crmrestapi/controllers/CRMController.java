package com.theam.CRMService.crmrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.IResponse;
import com.theam.CRMService.crmrestapi.models.QuickResponse;

@Component
public class CRMController {
	
	
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
	
	public IResponse CreateCustomer(String name, String surname) {
		
		return CustomerService.CreateCustomer(name, surname);
	}
	
	public IResponse DeleteCustomer(Integer customerID) {
		return CustomerService.DeleteCustomer(customerID);
	}
	public IResponse UpdateCustomer(Integer customerID, String name,String surname) {
		return CustomerService.UpdateCustomer(customerID, name, surname);
	}
	
	public IResponse UploadImage(String customerID, String file) {
		return new QuickResponse("Uploaded image for customer: " + customerID +" :"+file);
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
