package com.theam.CRMService.crmrestapi.exceptions.internal;

public class NoPageContentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641409378865047234L;

	public NoPageContentException(String info){
		super(info);
	}
}
