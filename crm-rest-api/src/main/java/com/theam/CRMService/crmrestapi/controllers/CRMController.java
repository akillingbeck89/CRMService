package com.theam.CRMService.crmrestapi.controllers;

import org.springframework.stereotype.Component;

import com.theam.CRMService.crmrestapi.models.IResponse;
import com.theam.CRMService.crmrestapi.models.QuickResponse;

@Component
public class CRMController {
	
	
	public IResponse GetCustomers() {
		
		return new QuickResponse("Getting Customers");
	}
	
	public IResponse GetCustomerDetails(String customerID) {
		return new QuickResponse("Getting Details for Customer " + customerID);
	}
	
	public IResponse CreateCustomer(String name, String surname) {
		
		return new QuickResponse("Creating customer "+name + " " + surname);
	}
	
	public IResponse DeleteCustomer(String customerID) {
		return new QuickResponse("Deleting customer " + customerID);
	}
	public IResponse UpdateCustomer(String customerID, String newDetails) {
		return new QuickResponse("Updating customer " + customerID + " With " + newDetails);
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
