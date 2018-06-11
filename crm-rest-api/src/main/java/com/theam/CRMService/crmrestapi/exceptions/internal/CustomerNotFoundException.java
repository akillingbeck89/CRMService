package com.theam.CRMService.crmrestapi.exceptions.internal;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5879929636587875508L;

	public CustomerNotFoundException(long id) {
		super(String.format("Customer not found with id %d", id));
	}
}
