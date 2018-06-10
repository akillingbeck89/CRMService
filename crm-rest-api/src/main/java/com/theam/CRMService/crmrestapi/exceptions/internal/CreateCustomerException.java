package com.theam.CRMService.crmrestapi.exceptions.internal;

public class CreateCustomerException extends RuntimeException {

	public CreateCustomerException(String message) {
		super("Couldn't create customer Reason: " + message);
	}
}
