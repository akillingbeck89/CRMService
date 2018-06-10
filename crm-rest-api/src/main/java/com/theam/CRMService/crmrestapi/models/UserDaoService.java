package com.theam.CRMService.crmrestapi.models;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.exceptions.internal.NoPageContentException;
import com.theam.CRMService.crmrestapi.exceptions.internal.UserNotFoundException;
import com.theam.CRMService.crmrestapi.exceptions.internal.WrongInputException;
import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.repository.UserRepository;

@Component
public class UserDaoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private UserRepository Repository;

	public ResponseEntity<User> CreateUser(User user) {
		try {
			Repository.save(user);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		catch(IllegalArgumentException e) {
			throw new WrongInputException("Input was not a user");
		}
	}

	public List<User> GetUsers(int start,int stride){
		Page<User> page = Repository.findAll(PageRequest.of(start, stride));
		
		if(page.hasContent()) {
			return page.getContent();
		}
		throw new NoPageContentException(String.format("No User Pages %d-%d", start,stride));
	}

	public void DeleteUser(long id) {
		
		if(Repository.existsById(id)) {
			Repository.deleteById(id);
			return;
		}
		throw new UserNotFoundException(id);
	
	}

	public User UpdateUser(long id, User pUser) {
		Optional<User> user = Repository.findById(id);
		if(user.isPresent()) {
			user.get().setUserName(pUser.getUserName());
			user.get().setHasAdminRights(pUser.getHasAdminRights());
			Repository.save(user.get());
			return user.get();
		}
		throw new UserNotFoundException(id);

	}

}
