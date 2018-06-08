package com.theam.CRMService.crmrestapi.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theam.CRMService.crmrestapi.models.QuickResponse;

@RestController
public class CRMController {
	

	///User authorised API calls
	@GetMapping("/api/getCustomers")
	public QuickResponse GetCustomers() {
		
		return new QuickResponse("/api/getCustomers");
	}

	@GetMapping("/api/getCustomerDetails")
	public QuickResponse GetCustomerDetails(@RequestParam String customerID) {
		
		return new QuickResponse("/api/getCustomerDetails/: "+customerID);
	}

	@PostMapping("/api/createCustomer")
	public QuickResponse CreateCustomer(@RequestParam String name, @RequestParam String surname ) {
		return new QuickResponse("/api/createCustomer: " + name + " " + surname);
	}

	@DeleteMapping("/api/deleteCustomer")
	public QuickResponse DeleteCustomer(@RequestParam String customerID) {
		return new QuickResponse("/api/deleteCustomer:"+customerID);
	}
	@PutMapping("/api/updateCustomer")
	public QuickResponse updateCustomer(@RequestParam String customerID,@RequestParam String details) {
		return new QuickResponse("/api/updateCustomer: " + customerID + ":"+details);
	}
	@PutMapping("/api/uploadPhoto")
	public QuickResponse UploadImage(@RequestParam String customerID, @RequestParam String file) {
		return new QuickResponse("/api/uploadPhoto: " + file);
	}
	
	//Admin authorised API calls
	@PostMapping("/api/createUser")
	public QuickResponse CreateUser(@RequestParam String details) {
		return new QuickResponse("/api/createUser: " + details);
	}
	@DeleteMapping("/api/deleteUser")
	public QuickResponse DeleteUser(@RequestParam String userID) {
		return new QuickResponse("/api/deleteUser: " + userID);
	}
	@PutMapping("/api/updateUser")
	public QuickResponse UpdateUser(@RequestParam String userID,@RequestParam String details) {
		return new QuickResponse("/api/updateUser: " + userID + ":" + details);
	}
	@GetMapping("/api/getUsers")
	public QuickResponse GetUsers() {
		return new QuickResponse("/api/getUsers");
	}
	@PutMapping("/api/promoteUser")
	public QuickResponse PromoteUser(@RequestParam String userID, @RequestParam int to) {
		return new QuickResponse("/api/promoteUser: " + userID + ":" + to);
	}
	@PutMapping("/api/demoteUser")
	public QuickResponse DemoteUser(@RequestParam String userID, @RequestParam int to) {
		return new QuickResponse("/api/demoteUser: " + userID + ":" + to);
	}
	

}
