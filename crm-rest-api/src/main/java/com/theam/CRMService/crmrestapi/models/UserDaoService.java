package com.theam.CRMService.crmrestapi.models;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.Users.Users;

@Service
public class UserDaoService {
	
	private static List<User> s_users = new ArrayList<User>();
	private static int s_count = 0;
	public ResponseEntity<Object> CreateUser(User puser) {
		
		User user = new User(s_count++,puser.getUserName(),puser.getPassWord(),puser.getHasAdminRights());
		s_users.add(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/*TO REPLACE WITH ACTUAL DB*/
	public ResponseEntity<Object> GetUsers(int start,int stride){

		try {
		return ResponseEntity.ok(new Users(s_users.subList(start, start+stride)));
		}
		catch(IndexOutOfBoundsException e) {
			return ResponseEntity.noContent().build();
		}
	}

	public ResponseEntity<Object> DeleteUser(int id) {
		Iterator<User> itr = s_users.iterator();
		while(itr.hasNext()){
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return ResponseEntity.accepted().body(user);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<Object> PromoteUser(int id,int to) {
		for(User user:s_users) {
			if(user.getId()==id) {
				user.setHasAdminRights(to > 0);
				return ResponseEntity.ok(user);
			}
		}
		
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Object> UpdateUser(int id, String username, String password,boolean giveAdminRights) {

		for(User user:s_users) {
		if(user.getId()==id) {
				user.setUserName(username != null ? username : user.getUserName());
				user.setPassWord(password != null ? password : user.getPassWord());
				user.setHasAdminRights(giveAdminRights);
				return ResponseEntity.ok().body(user);
			}
		}
		
		return ResponseEntity.notFound().build();
	}

}
