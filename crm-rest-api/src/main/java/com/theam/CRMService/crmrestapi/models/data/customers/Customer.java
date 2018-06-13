package com.theam.CRMService.crmrestapi.models.data.customers;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.theam.CRMService.crmrestapi.models.data.Users.User;



@Entity(name="customers")
public class Customer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1264109838117583765L;
	@Id
	@GeneratedValue
	private int id;
	@Size(min=2,max=64,message="First name must be between 3 and 64 characters long")
	@NotNull(message="firstname: was null")
	@SafeHtml(whitelistType=WhiteListType.NONE,message="XSS PREVENTION")
	private String firstname;
	@Size(min=3,max=128,message="Last name must be between 3 and 128 characters long")
	@NotNull(message="lastname: was null")
	@SafeHtml(whitelistType=WhiteListType.NONE,message="XSS PREVENTION")
	private String lastname;
	private URL photoPath;

	@OneToOne
	private User createdby_user;
	@OneToOne
	private User lastmodifiedby_user;
	
	@Email(message="Must be a valid email")
	@NotNull(message="email: was null")
	@SafeHtml(whitelistType=WhiteListType.NONE,message="XSS PREVENTION")
	private String email;
	
	protected Customer() {
		
	}
	public Customer(int id, String firstname, String lastname,String email) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email =email;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public URL getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(URL photoPath) {
		this.photoPath = photoPath;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	
	public User getCreatedby() {
		return createdby_user;
	}
	public void setCreatedby(User createdby) {
		this.createdby_user = createdby;
	}
	
	public User getLastmodifiedby() {
		return lastmodifiedby_user;
	}
	public void setLastmodifiedby(User lastmodifiedby) {
		this.lastmodifiedby_user = lastmodifiedby;
	}
	@Override
	public String toString() {
		return "Customer [m_id=" + this.id + ", m_foreName=" + this.firstname + ", m_surName=" + this.lastname + "]";
	}
}
