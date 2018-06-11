package com.theam.CRMService.crmrestapi.security.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.theam.CRMService.crmrestapi.models.data.Users.User;

public class CRMUserAdapter implements UserDetails{

	 private static final long serialVersionUID = -1360188483928178893L;
	  private User user;
	  public CRMUserAdapter(User user) {
	    this.user = user;
	  }
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	    user.getRoles().stream().forEach(authorities::add);
	    return authorities;
	  }
	  @Override
	  public String getPassword() {
	    return user.getPassword();
	  }
	  @Override
	  public String getUsername() {
	    return user.getUsername();
	  }
	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }
	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }
	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }
	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

}
