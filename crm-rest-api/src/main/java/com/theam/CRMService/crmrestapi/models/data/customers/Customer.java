package com.theam.CRMService.crmrestapi.models.data.customers;

import java.net.URI;

import com.theam.CRMService.crmrestapi.models.IResponse;

public class Customer implements IResponse{
	private Integer m_id;
	private String m_foreName;
	private String m_surName;
	private URI m_photoPath;
	private String m_email;
	

	public Customer(Integer id, String foreName, String surName,String email) {
		m_id = id;
		m_foreName = foreName;
		m_surName = surName;
		m_email =email;
	}

	public String getEmail() {
		return m_email;
	}
	public void setEmail(String email) {
		m_email = email;
	}
	public URI getPhotoPath() {
		return m_photoPath;
	}

	public void setPhotoPath(URI photoPath) {
		m_photoPath = photoPath;
	}

	public Integer getId() {
		return m_id;
	}
	
	public void setId(Integer id) {
		m_id = id;
	}
	public String getForeName() {
		return m_foreName;
	}
	public void setForeName(String foreName) {
		m_foreName = foreName;
	}
	public String getSurName() {
		return m_surName;
	}
	public void setSurName(String surName) {
		m_surName = surName;
	}

	@Override
	public String toString() {
		return "Customer [m_id=" + m_id + ", m_foreName=" + m_foreName + ", m_surName=" + m_surName + "]";
	}
}
