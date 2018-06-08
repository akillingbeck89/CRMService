package com.theam.CRMService.crmrestapi.models.data;

import com.theam.CRMService.crmrestapi.models.IResponse;

public class Customer implements IResponse{
	private Integer m_id;
	private String m_foreName;
	private String m_surName;
	private String m_photoPath;
	

	public Customer(Integer id, String foreName, String surName, String photoPath) {
		m_id = id;
		m_foreName = foreName;
		m_surName = surName;
		m_photoPath = photoPath;
	}

	public String getPhotoPath() {
		return m_photoPath;
	}

	public void setPhotoPath(String photoPath) {
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
