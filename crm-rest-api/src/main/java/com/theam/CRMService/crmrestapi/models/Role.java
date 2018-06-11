package com.theam.CRMService.crmrestapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theam.CRMService.crmrestapi.models.data.Users.User;

@Entity(name="role")
public class Role implements GrantedAuthority, Serializable{
	
	private static final long serialVersionUID = -1562723181766676778L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String role;

	@JsonIgnore
	@ManyToMany(mappedBy="roles")
	private List<User> users = new ArrayList<User>();
	
	
	protected Role() {
		
	}
	public Role(String role) {
		super();
		this.role = role;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<User> getUser() {
		return users;
	}
	public void setUser(List<User> user) {
		this.users = user;
	}
	
	@Override
	public String getAuthority() {
		return this.role;
	}
	
	
	

}
