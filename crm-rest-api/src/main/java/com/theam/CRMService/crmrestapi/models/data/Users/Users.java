package com.theam.CRMService.crmrestapi.models.data.Users;

import java.util.List;

public class Users {
private List<User> m_users;
	
	public Users(List<User> users) {
		m_users = users;
	}
	public List<User> getUsers() {
		return m_users;
	}
}
