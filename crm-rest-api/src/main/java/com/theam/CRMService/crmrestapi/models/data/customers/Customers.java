package com.theam.CRMService.crmrestapi.models.data.customers;

import java.util.List;

//Wrapper IResponse for a List of Customers
public class Customers{
	private List<Customer> m_customers;
	
	public Customers(List<Customer> customers) {
		m_customers = customers;
	}
	public List<Customer> getCustomers() {
		return m_customers;
	}
}