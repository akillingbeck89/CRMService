package com.theam.CRMService.crmrestapi.models.data.customers;

import java.util.List;

import com.theam.CRMService.crmrestapi.models.IResponse;

//Wrapper IResponse for a List of Customers
public class Customers implements IResponse{
	private List<Customer> m_customers;
	
	public Customers(List<Customer> customers) {
		m_customers = customers;
	}
	public List<Customer> getCustomers() {
		return m_customers;
	}
}