package com.theam.CRMService.crmrestapi.exceptions.internal;

public class DeletePhotoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -918624250672366131L;
	private Exception m_bubbledException;
	public DeletePhotoException(Exception initial) {
		super("Couldn't delete photo " + initial.getMessage());
		m_bubbledException = initial;
	}
	public Exception getBubbledException() {
		return m_bubbledException;
	}
	
}
