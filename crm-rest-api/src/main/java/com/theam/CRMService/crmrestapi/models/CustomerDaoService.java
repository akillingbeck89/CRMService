package com.theam.CRMService.crmrestapi.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theam.CRMService.crmrestapi.models.data.Customer;
import com.theam.CRMService.crmrestapi.models.data.Customers;


@Service
public class CustomerDaoService {
	
	//TODO: Replace static list with Db access
	private static List<Customer> s_customers = new ArrayList<Customer>();
	private static Integer s_count = 0;
	
	static {
		//Mock data
		for(int i=0;i<10;i++) {
			s_customers.add(new Customer(i,String.format("Name %s", i),String.format("Fname %s", i-1),"photouri"));
			s_count++;
		}
	}
	
	/*TO REPLACE WITH ACTUAL DB*/
	public IResponse GetCustomers(int start,int stride){
		try {
		return new Customers(s_customers.subList(start, start+stride));
		}
		catch(IndexOutOfBoundsException e) {
			return new QuickResponse("Not enough customers");
		}
	}
	
	public IResponse CreateCustomer(String name, String surname) {
		Customer customer = new Customer(s_count++,name,surname,"photourl");
		s_customers.add(customer);
		
		return customer;
	}

	public IResponse DeleteCustomer(int id) {
		for(Customer customer:s_customers) {
			if(customer.getId()==id) {
				s_customers.remove(customer);
				return new QuickResponse("Deleted user "+id);
			}
		}
		
		return new QuickResponse("Customer not found");
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
				customer.setSurName(surname);
				customer.setForeName(name);
				
				return customer;
			}
		}
		
		return new QuickResponse("Customer not found");
	}
}
