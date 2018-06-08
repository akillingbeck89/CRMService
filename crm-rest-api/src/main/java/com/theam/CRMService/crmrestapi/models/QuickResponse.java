package com.theam.CRMService.crmrestapi.models;


public class QuickResponse {
	
	private String m_response = "Success";
	public QuickResponse(String response) {
		m_response = "Success" + response;
	}

	public String getResponse() {
		return m_response;
	}

	
}
