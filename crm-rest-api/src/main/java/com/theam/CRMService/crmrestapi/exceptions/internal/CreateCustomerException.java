package com.theam.CRMService.crmrestapi.exceptions.internal;

public class CreateCustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8265444915750451733L;

	public CreateCustomerException(String message) {
		super("Couldn't create customer Reason: " + message);
	}
}
