package com.theam.CRMService.crmrestapi.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.repository.UserRepository;
@Service
public class CRMUserDetailsService implements UserDetailsService 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	@Autowired
	public CRMUserDetailsService(UserRepository repository) {
		userRepository = repository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(String.format("User %s not found!", username));
		}
		
		return new CRMUserAdapter(user);
	}
	
	 public User saveUser(User user) {
		 	user.setPassword(passwordEncoder.encode(user.getPassword()));
		    return userRepository.save(user);
		 }

}
