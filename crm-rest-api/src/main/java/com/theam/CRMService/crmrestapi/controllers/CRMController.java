package com.theam.CRMService.crmrestapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.UserDaoService;
import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.customers.Customer;

@Component
public class CRMController {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private CustomerDaoService CustomerService;
	@Autowired
	private UserDaoService UserService;


	public ResponseEntity<Object> GetCustomers(Integer startIndex,Integer limit) {
		
		return CustomerService.GetCustomers(startIndex,limit);
	}
	
	public ResponseEntity<Object> GetCustomerDetails(int id) {
		return CustomerService.GetCustomerDetails(id);
	}
	
	public ResponseEntity<Object> CreateCustomer(Customer customer) {
		
		return CustomerService.CreateCustomer(customer.getForeName(), customer.getSurName(),customer.getEmail());
	}
	
	public ResponseEntity<Object> DeleteCustomer(int id) {
		return CustomerService.DeleteCustomer(id);
	}
	public ResponseEntity<Object> UpdateCustomer(int id, Customer customer) {
		return CustomerService.UpdateCustomer(id, customer.getForeName(), customer.getSurName());
	}
	
	public ResponseEntity<Object> UploadCustomerPhoto(int id, MultipartFile file) {
		
		return CustomerService.UpdateCustomerPhoto(id, file);
	}
	public ResponseEntity<Object> DeleteCustomerPhoto(int id){
		return CustomerService.DeleteCustomerPhoto(id);
	}
	
	
	public ResponseEntity<Object> CreateUser(User user) {
		return UserService.CreateUser(user);
	}
	
	public ResponseEntity<Object> DeleteUser(int id) {
		return UserService.DeleteUser(id);
	}
	
	public ResponseEntity<Object> UpdateUser(int id, User user) {
		return UserService.UpdateUser(id, user.getUserName(), user.getPassWord(), user.getHasAdminRights());
	}
	public ResponseEntity<Object> GetUsers(Integer start, Integer stride) {
		return UserService.GetUsers(start, stride);
	}
	
}
