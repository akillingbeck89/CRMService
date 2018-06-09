package com.theam.CRMService.crmrestapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.IResponse;
import com.theam.CRMService.crmrestapi.models.QuickResponse;
import com.theam.CRMService.crmrestapi.models.UserDaoService;

@Component
public class CRMController {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private CustomerDaoService CustomerService;
	@Autowired
	private UserDaoService UserService;


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
	
	
	public IResponse CreateUser(String username, String password,boolean giveAdminRights) {
		return UserService.CreateUser(username, password,giveAdminRights);
	}

	public IResponse DeleteUser(Integer userID) {
		return UserService.DeleteUser(userID);
	}
	
	public IResponse UpdateUser(Integer userID ,String username,String password,Boolean giveAdminRights) {
		return UserService.UpdateUser(userID, username, password, giveAdminRights);
	}
	public IResponse GetUsers(Integer start, Integer stride) {
		return UserService.GetUsers(start, stride);
	}
	
}
