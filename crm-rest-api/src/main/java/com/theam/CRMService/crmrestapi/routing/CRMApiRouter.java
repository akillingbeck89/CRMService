package com.theam.CRMService.crmrestapi.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theam.CRMService.crmrestapi.controllers.CRMController;
import com.theam.CRMService.crmrestapi.models.IResponse;

@RestController
public class CRMApiRouter {
	
	@Autowired//Inject controller
	private CRMController Controller;
	/*
	* 			User authorised API calls
	* 
	* */
	
	//TODO: Add pageination
	@GetMapping("/api/getCustomers")
	public IResponse GetCustomers() {
		
		return Controller.GetCustomers();
	}

	@GetMapping("/api/getCustomerDetails/{customerID}")
	public IResponse GetCustomerDetails(@PathVariable String customerID) {
		
		return Controller.GetCustomerDetails(customerID);
	}

	@PostMapping("/api/createCustomer")
	public IResponse CreateCustomer(@RequestParam String name, @RequestParam String surname ) {
		return Controller.CreateCustomer(name, surname);
	}

	@DeleteMapping("/api/deleteCustomer/{customerID}")
	public IResponse DeleteCustomer(@PathVariable String customerID) {
		return Controller.DeleteCustomer(customerID);
	}
	@PutMapping("/api/updateCustomer/{customerID}")
	public IResponse updateCustomer(@PathVariable String customerID,@RequestParam String details) {
		return Controller.UpdateCustomer(customerID, details);
	}
	@PutMapping("/api/uploadPhoto/{customerID}")
	public IResponse UploadImage(@PathVariable String customerID, @RequestParam String file) {
		return Controller.UploadImage(customerID, file);
	}
	
	/*
				Admin authorised API calls
	 * 
	 * 
	 */
	@PostMapping("/api/createUser/{userID}")
	public IResponse CreateUser(@PathVariable String userID, @RequestParam String details) {
		return Controller.CreateUser(userID, details);
	}
	@DeleteMapping("/api/deleteUser/{userID}")
	public IResponse DeleteUser(@PathVariable String userID) {
		return Controller.DeleteUser(userID);
	}
	@PutMapping("/api/updateUser/{userID}")
	public IResponse UpdateUser(@PathVariable String userID,@RequestParam String details) {
		return Controller.UpdateUser(userID, details);
	}
	
	//TODO: Add pageination
	@GetMapping("/api/getUsers")
	public IResponse GetUsers() {
		return Controller.GetUsers();
	}
	@PutMapping("/api/promoteUser/{userID}")
	public IResponse PromoteUser(@PathVariable String userID, @RequestParam int to) {
		return Controller.PromoteUser(userID, to);
	}
	@PutMapping("/api/demoteUser/{userID}")
	public IResponse DemoteUser(@PathVariable String userID, @RequestParam int to) {
		return Controller.DemoteUser(userID, to);
	}
	
}
