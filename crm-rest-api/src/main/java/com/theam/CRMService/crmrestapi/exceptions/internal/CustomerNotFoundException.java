package com.theam.CRMService.crmrestapi.exceptions.internal;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(long id) {
		super(String.format("Customer not found with id %d", id));
	}
}
