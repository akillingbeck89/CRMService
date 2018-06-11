package com.theam.CRMService.crmrestapi.models.data.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.theam.CRMService.crmrestapi.models.Role;



@Entity(name="users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3831350175634992201L;

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=3,message = "Name must be at least 3 characters long")
	private String username;
	
	@Size(min=4,message="Password must be at least 4 characters long")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="users_role", joinColumns= @JoinColumn(name="users_id",referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="role_id",referencedColumnName="id"))
    private List<Role> roles = new ArrayList<Role>();
	
	private boolean enabled;
	protected User() {
		
	}
	public User(int id, String userName, String passWord,boolean enabled) {
		this.id = id;
		username = userName;
		password = passWord;
		this.enabled = enabled;
	}
	
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
