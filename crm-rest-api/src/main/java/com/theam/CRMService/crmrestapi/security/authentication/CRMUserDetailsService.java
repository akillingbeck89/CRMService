package com.theam.CRMService.crmrestapi.security.authentication;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.exceptions.internal.NoPageContentException;
import com.theam.CRMService.crmrestapi.exceptions.internal.UserNotFoundException;
import com.theam.CRMService.crmrestapi.exceptions.internal.WrongInputException;
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
	
	public static User currentUserFromContext() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth= securityContext.getAuthentication();
		
		if(auth != null) {
			if(auth.getPrincipal() instanceof CRMUserAdapter) {
				CRMUserAdapter userDetails= (CRMUserAdapter)auth.getPrincipal();
				return userDetails.getUser();
			}
		}
		
		return null;
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
	 
	 
	 public ResponseEntity<User> CreateUser(User user) {
			try {
	
				User saved = saveUser(user);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
				
				return ResponseEntity.created(location).build();
			}
			catch(IllegalArgumentException e) {
				throw new WrongInputException("Input was not a user");
			}
		}

		public List<User> GetUsers(int start,int stride){
			
			if(start > 0 && stride > 0) {
				Page<User> page = userRepository.findAll(PageRequest.of(start, stride));
				
				if(page.hasContent()) {
					return page.getContent();
				}
				throw new NoPageContentException(String.format("No User Pages %d-%d", start,stride));
			}
			
			return userRepository.findAll();
		
		}

		public void DeleteUser(int id) {
			
			if(userRepository.existsById(id)) {
				userRepository.deleteById(id);
				return;
			}
			throw new UserNotFoundException(id);
		
		}

		public User UpdateUser(int id, User pUser) {
			Optional<User> user = userRepository.findById(id);
			if(user.isPresent()) {
				pUser.setId(id);
				return userRepository.save(pUser);
			}
			throw new UserNotFoundException(id);

		}

}
