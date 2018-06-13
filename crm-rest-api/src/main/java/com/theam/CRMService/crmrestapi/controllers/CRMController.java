package com.theam.CRMService.crmrestapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.theam.CRMService.crmrestapi.models.CustomerDaoService;
import com.theam.CRMService.crmrestapi.models.Role;
import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.customers.Customer;
import com.theam.CRMService.crmrestapi.security.authentication.CRMUserDetailsService;

@RestController
@RequestMapping("/api")
public class CRMController {
	
	@Autowired
	private CustomerDaoService CustomerService;
	@Autowired
	private CRMUserDetailsService UserDetailsService;
	
	 @PostConstruct
	  public void init() {
	    User user = new User(0,"super_user","c757b8bf1d6a0e933c98390ce32276f0",true);
	    List<Role> roles1 = new ArrayList<>();
	    roles1.add(new Role("ROLE_ADMIN"));
	    roles1.add(new Role("ROLE_USER"));
	    user.setRoles(roles1);
	    UserDetailsService.saveUser(user);
	    
	  }
	/*
	* 			User authorised API calls
	* 
	* */
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> CreateCustomer(@Valid @RequestBody Customer customer) {
		return CustomerService.CreateCustomer(customer);
	}
	@GetMapping("/customers")
	public List<Customer> GetCustomers(@RequestParam Optional<Integer> start, @RequestParam Optional<Integer> stride) {
		
		return CustomerService.GetCustomers(start.isPresent() ? start.get() : -1,stride.isPresent() ? stride.get() : -1);
	}

	@GetMapping("/customers/{id}")
	public Customer GetCustomerDetails(@PathVariable int id) {
		
		return CustomerService.GetCustomerDetails(id);
	}
	@DeleteMapping("/customers/{id}")
	public void DeleteCustomer(@PathVariable int id) {
		CustomerService.DeleteCustomer(id);
	}
	
	@PutMapping("/customers/{id}")
	public Customer UpdateCustomer(@PathVariable int id,@Valid @RequestBody Customer customer) {
		
		return CustomerService.UpdateCustomer(id,customer);
	}
	@PutMapping("/customers/{id}/photo")
	public ResponseEntity<Object> UploadImage(@PathVariable int id, @RequestParam MultipartFile file) {
		return CustomerService.UpdateCustomerPhoto(id, file);
	}
	@DeleteMapping("/customers/{id}/photo")
	public void DeleteImage(@PathVariable int id){
		CustomerService.DeleteCustomerPhoto(id);
	}
	
	/*
				Admin authorised API calls
	 * 
	 * 
	 */
	@PostMapping("/users")
	public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
		return UserDetailsService.CreateUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void DeleteUser(@PathVariable int id) {
		UserDetailsService.DeleteUser(id);
	}
	@PutMapping("/users/{id}")
	public User UpdateUser(@PathVariable int id, @Valid @RequestBody User user) {
		return UserDetailsService.UpdateUser(id,user);
	}
	
	@GetMapping("/users")
	public List<User> GetUsers(@RequestParam Integer start, @RequestParam Integer stride) {
		return UserDetailsService.GetUsers(start,stride);
	}

}
