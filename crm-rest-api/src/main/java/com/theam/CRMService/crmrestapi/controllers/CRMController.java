package com.theam.CRMService.crmrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.UserDaoService;
import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.customers.Customer;

@RestController
public class CRMController {
	
	@Autowired
	private CustomerDaoService CustomerService;
	@Autowired
	private UserDaoService UserService;
	/*
	* 			User authorised API calls
	* 
	* */
	
	@PostMapping("/api/customers")
	public ResponseEntity<Customer> CreateCustomer(@RequestBody Customer customer) {
		return CustomerService.CreateCustomer(customer);
	}
	@GetMapping("/api/customers")
	public List<Customer> GetCustomers(@RequestParam Integer start, @RequestParam Integer stride) {
		
		return CustomerService.GetCustomers(start,stride);
	}

	@GetMapping("/api/customers/{id}")
	public Customer GetCustomerDetails(@PathVariable long id) {
		
		return CustomerService.GetCustomerDetails(id);
	}
	@DeleteMapping("/api/customers/{id}")
	public void DeleteCustomer(@PathVariable long id) {
		CustomerService.DeleteCustomer(id);
	}
	
	@PutMapping("/api/customers/{id}")
	public Customer UpdateCustomer(@PathVariable long id,@RequestBody Customer customer) {
		
		return CustomerService.UpdateCustomer(id,customer);
	}
	@PutMapping("/api/customers/{id}/photo")
	public ResponseEntity<Object> UploadImage(@PathVariable long id, @RequestParam MultipartFile file) {
		return CustomerService.UpdateCustomerPhoto(id, file);
	}
	@DeleteMapping("/api/customers/{id}/photo")
	public void DeleteImage(@PathVariable long id){
		CustomerService.DeleteCustomerPhoto(id);
	}
	
	/*
				Admin authorised API calls
	 * 
	 * 
	 */
	@PostMapping("/api/users")
	public ResponseEntity<User> CreateUser(@RequestBody User user) {
		return UserService.CreateUser(user);
	}
	
	@DeleteMapping("/api/users/{id}")
	public void DeleteUser(@PathVariable long id) {
		UserService.DeleteUser(id);
	}
	@PutMapping("/api/users/{id}")
	public User UpdateUser(@PathVariable long id, @RequestBody User user) {
		return UserService.UpdateUser(id,user);
	}
	
	@GetMapping("/api/users")
	public List<User> GetUsers(@RequestParam Integer start, @RequestParam Integer stride) {
		return UserService.GetUsers(start,stride);
	}

}
