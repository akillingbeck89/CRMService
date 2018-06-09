package com.theam.CRMService.crmrestapi.models.data.Users;

import java.util.List;

import com.theam.CRMService.crmrestapi.models.IResponse;

public class Users implements IResponse {
private List<User> m_users;
	
	public Users(List<User> users) {
		m_users = users;
	}
	public List<User> getUsers() {
		return m_users;
	}
}
