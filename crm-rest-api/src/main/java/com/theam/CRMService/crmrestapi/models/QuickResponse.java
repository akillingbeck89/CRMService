package com.theam.CRMService.crmrestapi.models;


public class QuickResponse implements IResponse {
	
	private String m_response = "Success";
	public QuickResponse(String response) {
		m_response =  response;
	}

	public String getResponse() {
		return m_response;
	}

	
}
