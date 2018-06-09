package com.theam.CRMService.crmrestapi.models;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public IResponse GetCustomers(int start,int stride){
		try {
		return new Customers(s_customers.subList(start, start+stride));
		}
		catch(IndexOutOfBoundsException e) {
			return new QuickResponse("Not enough customers");
		}
	}
	
	public IResponse UpdateCustomerPhoto(int customerID,MultipartFile file) {
		
		if(doesCustomerExist(customerID)) {
			try {
					URI uri = FileStorage.store(file,"customers",String.valueOf(customerID));
					for(Customer customer:s_customers) {
						if(customer.getId()==customerID) {
							customer.setPhotoPath(uri);
							return customer;
						}
					}
			} 
			catch (IOException e) {
				return new QuickResponse(e.getMessage());
			}

		}
		
		return new QuickResponse("No Customer with iD "+customerID);
	}
	public IResponse CreateCustomer(String name, String surname,String email) {
		Customer customer = new Customer(s_count++,name,surname,email);
		s_customers.add(customer);
		
		return customer;
	}

	public IResponse DeleteCustomer(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				s_customers.remove(customer);
				return new QuickResponse("Deleted customer "+id);
			}
		}
		
		return new QuickResponse("Customer not found");
	}
	
	public boolean doesCustomerExist(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				return true;
			}
		}
		
		return false;
	}
	public IResponse GetCustomerDetails(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		
		return new QuickResponse("Customer not found");
	}
	
	public IResponse UpdateCustomer(int id, String name, String surname) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				customer.setSurName(surname != null ? surname : customer.getSurName());
				customer.setForeName(name != null ? name : customer.getForeName());
				
				return customer;
			}
		}
		
		return new QuickResponse("Customer not found");
	}
}
