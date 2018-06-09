package com.theam.CRMService.crmrestapi.models;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.models.data.customers.Customer;
import com.theam.CRMService.crmrestapi.models.data.customers.Customers;
import com.theam.CRMService.crmrestapi.utils.FileStorageService;

@Service
public class CustomerDaoService {
	
	@Autowired
	private FileStorageService FileStorage;
	//TODO: Replace static list with Db access
	private static List<Customer> s_customers = new ArrayList<Customer>();
	private static Integer s_count = 0;

	/*TO REPLACE WITH ACTUAL DB*/
	public ResponseEntity<Object> GetCustomers(int start,int stride){
		try {
			return ResponseEntity.ok().body(new Customers(s_customers.subList(start, start+stride)));
		}
		catch(IndexOutOfBoundsException e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	public ResponseEntity<Object> DeleteCustomerPhoto(int customerID){
		if(doesCustomerExist(customerID)) {

			for(Customer customer:s_customers) {
				if(customer.getId()==customerID) {
					try {
						FileStorage.deleteFile(customer.getPhotoPath().toURI());
						customer.setPhotoPath(null);
						return ResponseEntity.accepted().build();
					}
					catch(Exception e) {
						return ResponseEntity.badRequest().build();
					}
				}
			}
			
		}
		
		return ResponseEntity.noContent().build();
	}
	public ResponseEntity<Object> UpdateCustomerPhoto(int customerID,MultipartFile file) {
		if(doesCustomerExist(customerID)) {
			try {
					URI uri = FileStorage.store(file,"customers",String.valueOf(customerID));
					for(Customer customer:s_customers) {
						if(customer.getId()==customerID) {
							customer.setPhotoPath(uri.toURL());
							return ResponseEntity.created(uri).build();
						}
					}
			} 
			catch (IOException e) {
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
			}

		}
		
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<Object> CreateCustomer(String name, String surname,String email) {
		Customer customer = new Customer(s_count++,name,surname,email);
		s_customers.add(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(location).build();
	
	}

	public ResponseEntity<Object> DeleteCustomer(int id) {
		
		Iterator<Customer> itr = s_customers.iterator();
		while(itr.hasNext()) {
			Customer customer = itr.next();
			if(customer.getId()==id) {
				itr.remove();
				return ResponseEntity.accepted().body(customer);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	public boolean doesCustomerExist(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				return true;
			}
		}
		
		return false;
	}
	public ResponseEntity<Object> GetCustomerDetails(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId() == id) {
				return ResponseEntity.ok(customer);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Object> UpdateCustomer(int id, String name, String surname) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				customer.setSurName(surname != null ? surname : customer.getSurName());
				customer.setForeName(name != null ? name : customer.getForeName());
				
				return ResponseEntity.ok(customer);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
}
