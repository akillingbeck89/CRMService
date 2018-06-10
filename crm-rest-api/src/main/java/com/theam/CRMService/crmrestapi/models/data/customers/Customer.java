package com.theam.CRMService.crmrestapi.models.data.customers;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Customer{
	
	@Id
	@GeneratedValue
	private long m_id;
	private String m_foreName;
	private String m_surName;
	private URL m_photoPath;
	private String m_email;
	
	protected Customer() {
		
	}
	public Customer(long id, String foreName, String surName,String email) {
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
	public URL getPhotoPath() {
		return m_photoPath;
	}

	public void setPhotoPath(URL photoPath) {
		m_photoPath = photoPath;
	}

	public long getId() {
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
