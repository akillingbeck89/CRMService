package com.theam.CRMService.crmrestapi.models.data.Users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User{
	
	@Id
	@GeneratedValue
	private long m_id;
	
	private String m_userName;
	private String m_passWord;
	private boolean m_hasAdminRights;
	
	protected User() {
		
	}
	
	public User(long id, String userName, String passWord,Boolean hasAdminRights) {
		m_id = id;
		m_userName = userName;
		m_passWord = passWord;
		m_hasAdminRights = hasAdminRights;
	}
	
	public boolean getHasAdminRights() {
		return m_hasAdminRights;
	}
	public void setHasAdminRights(Boolean b) {
		m_hasAdminRights = b;
	}
	
	public long getId() {
		return m_id;
	}
	public void setId(long id) {
		m_id = id;
	}
	public String getUserName() {
		return m_userName;
	}
	public void setUserName(String userName) {
		m_userName = userName;
	}
	public String getPassWord() {
		return m_passWord;
	}
	public void setPassWord(String passWord) {
		m_passWord = passWord;
	}
	
	
}
