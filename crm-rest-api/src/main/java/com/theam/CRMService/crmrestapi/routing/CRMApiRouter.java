package com.theam.CRMService.crmrestapi.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/api/getCustomers")
	public IResponse GetCustomers(@RequestParam Integer start, @RequestParam Integer stride) {
		
		return Controller.GetCustomers(start,stride);
	}

	@GetMapping("/api/getCustomerDetails/{customerID}")
	public IResponse GetCustomerDetails(@PathVariable Integer customerID) {
		
		return Controller.GetCustomerDetails(customerID);
	}

	@PostMapping("/api/createCustomer")
	public IResponse CreateCustomer(@RequestParam String name, @RequestParam String surname,@RequestParam String email) {
		return Controller.CreateCustomer(name, surname,email);
	}

	@DeleteMapping("/api/deleteCustomer/{customerID}")
	public IResponse DeleteCustomer(@PathVariable Integer customerID) {
		return Controller.DeleteCustomer(customerID);
	}
	@PutMapping("/api/updateCustomer/{customerID}")
	public IResponse updateCustomer(@PathVariable Integer customerID,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String surname) {
		return Controller.UpdateCustomer(customerID, name,surname);
	}
	@PutMapping("/api/uploadPhoto/{customerID}")
	public IResponse UploadImage(@PathVariable Integer customerID, @RequestParam MultipartFile file) {
		return Controller.UploadCustomerPhoto(customerID, file);
		
		
	}
	
	/*
				Admin authorised API calls
	 * 
	 * 
	 */
	@PostMapping("/api/createUser/")
	public IResponse CreateUser(@RequestParam String userName, @RequestParam String password, @RequestParam boolean giveAdminRights) {
		return Controller.CreateUser(userName, password,giveAdminRights);
	}
	@DeleteMapping("/api/deleteUser/{userID}")
	public IResponse DeleteUser(@PathVariable Integer userID) {
		return Controller.DeleteUser(userID);
	}
	@PutMapping("/api/updateUser/{userID}")
	public IResponse UpdateUser(@PathVariable Integer userID,
			@RequestParam(required=false) String username, 
			@RequestParam(required=false) String password,
			@RequestParam boolean giveAdminRights) {
		return Controller.UpdateUser(userID, username,password,giveAdminRights);
	}
	
	@GetMapping("/api/getUsers")
	public IResponse GetUsers(@RequestParam Integer start, @RequestParam Integer stride) {
		return Controller.GetUsers(start,stride);
	}

}
