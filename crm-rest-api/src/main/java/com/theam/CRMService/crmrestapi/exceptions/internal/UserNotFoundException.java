package com.theam.CRMService.crmrestapi.exceptions.internal;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8139112879853187506L;

	public UserNotFoundException(long id) {
		super(String.format("User not found with id %d", id));
	}
}
