package com.theam.CRMService.crmrestapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theam.CRMService.crmrestapi.models.data.Users.User;

@Entity(name="role")
public class Role implements GrantedAuthority, Serializable{
	
	private static final long serialVersionUID = -1562723181766676778L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@SafeHtml(whitelistType=WhiteListType.NONE,message="XSS Prevention")
	private String role;

	
	@ManyToMany(mappedBy="roles")
	private List<User> users = new ArrayList<User>();
	
	
	protected Role() {
		
	}
	public Role(String role) {
		super();
		this.role = role;
	}
	@JsonIgnore
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return this.role;
	}
	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> user) {
		this.users = user;
	}
	
	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.role;
	}
	
	
	

}
