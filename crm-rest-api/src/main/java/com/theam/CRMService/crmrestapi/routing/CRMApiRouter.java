package com.theam.CRMService.crmrestapi.routing;

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

import com.theam.CRMService.crmrestapi.controllers.CRMController;
import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.customers.Customer;

@RestController
public class CRMApiRouter {
	
	@Autowired//Inject controller
	private CRMController Controller;
	/*
	* 			User authorised API calls
	* 
	* */
	
	@PostMapping("/api/customers")
	public ResponseEntity<Object> CreateCustomer(@RequestBody Customer customer) {
		return Controller.CreateCustomer(customer);
	}
	@GetMapping("/api/customers")
	public ResponseEntity<Object> GetCustomers(@RequestParam Integer start, @RequestParam Integer stride) {
		
		return Controller.GetCustomers(start,stride);
	}

	@GetMapping("/api/customers/{id}")
	public ResponseEntity<Object> GetCustomerDetails(@PathVariable Integer id) {
		
		return Controller.GetCustomerDetails(id);
	}
	@DeleteMapping("/api/customers/{id}")
	public ResponseEntity<Object> DeleteCustomer(@PathVariable Integer id) {
		return Controller.DeleteCustomer(id);
	}
	
	@PutMapping("/api/customers/{id}")
	public ResponseEntity<Object> UpdateCustomer(@PathVariable Integer id,@RequestBody Customer customer) {
		return Controller.UpdateCustomer(id,customer);
	}
	@PutMapping("/api/customers/{id}/photo")
	public ResponseEntity<Object> UploadImage(@PathVariable Integer id, @RequestParam MultipartFile file) {
		return Controller.UploadCustomerPhoto(id, file);
	}
	@DeleteMapping("/api/customers/{id}/photo")
	public ResponseEntity<Object> DeleteImage(@PathVariable Integer id){
		return Controller.DeleteCustomerPhoto(id);
	}
	
	/*
				Admin authorised API calls
	 * 
	 * 
	 */
	@PostMapping("/api/users")
	public ResponseEntity<Object> CreateUser(@RequestBody User user) {
		return Controller.CreateUser(user);
	}
	
	@DeleteMapping("/api/users/{id}")
	public ResponseEntity<Object> DeleteUser(@PathVariable Integer id) {
		return Controller.DeleteUser(id);
	}
	@PutMapping("/api/users/{id}")
	public ResponseEntity<Object> UpdateUser(@PathVariable Integer id, @RequestBody User user) {
		return Controller.UpdateUser(id,user);
	}
	
	@GetMapping("/api/users")
	public ResponseEntity<Object> GetUsers(@RequestParam Integer start, @RequestParam Integer stride) {
		return Controller.GetUsers(start,stride);
	}

}
