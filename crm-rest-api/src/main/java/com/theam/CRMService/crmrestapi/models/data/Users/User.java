package com.theam.CRMService.crmrestapi.models.data.Users;

import com.theam.CRMService.crmrestapi.models.IResponse;

//A user can have access to a limited number of API functionality
public class User implements IResponse {
	private int m_id;
	private String m_userName;
	private String m_passWord;
	private boolean m_hasAdminRights;
	
	
	public User(int id, String userName, String passWord,Boolean hasAdminRights) {
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
	
	public int getId() {
		return m_id;
	}
	public void setId(int id) {
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
