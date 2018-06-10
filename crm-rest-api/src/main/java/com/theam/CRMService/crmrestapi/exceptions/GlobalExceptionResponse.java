package com.theam.CRMService.crmrestapi.exceptions;

import java.time.LocalDateTime;

public class GlobalExceptionResponse {
	
	private String m_reason;
	private String m_description;
	private LocalDateTime m_timestamp;
	
	
	public GlobalExceptionResponse(String reason, String description, LocalDateTime timestamp) {
		super();
		m_reason = reason;
		m_description = description;
		m_timestamp = timestamp;
	}
	public String getReason() {
		return m_reason;
	}
	public String getDescription() {
		return m_description;
	}
	public LocalDateTime getTimestamp() {
		return m_timestamp;
	}

}
