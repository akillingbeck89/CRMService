package com.theam.CRMService.crmrestapi.exceptions.internal;

public class DeletePhotoException extends RuntimeException {

	private Exception m_bubbledException;
	public DeletePhotoException(Exception initial) {
		super("Couldn't delete photo " + initial.getMessage());
		m_bubbledException = initial;
	}
	public Exception getBubbledException() {
		return m_bubbledException;
	}
	
}
