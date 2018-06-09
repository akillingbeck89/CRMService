package com.theam.CRMService.crmrestapi.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.theam.CRMService.crmrestapi.models.data.Users.User;
import com.theam.CRMService.crmrestapi.models.data.Users.Users;
import com.theam.CRMService.crmrestapi.models.IResponse;

@Service
public class UserDaoService {
	
	private static List<User> s_users = new ArrayList<User>();
	private static int s_count = 0;
	public IResponse CreateUser(String username,String password,boolean giveAdminRights) {
		
		User user = new User(++s_count,username,password,giveAdminRights);
		s_users.add(user);
		
		return user;
	}
	
	/*TO REPLACE WITH ACTUAL DB*/
	public IResponse GetUsers(int start,int stride){
		try {
		return new Users(s_users.subList(start, start+stride));
		}
		catch(IndexOutOfBoundsException e) {
			return new QuickResponse("Not enough users");
		}
	}

	public IResponse DeleteUser(int id) {
		for(User user:s_users) {
			if(user.getId()==id) {
				s_users.remove(user);
				return new QuickResponse("Deleted User "+id);
			}
		}
		
		return new QuickResponse("User not found");
	}
	public IResponse PromoteUser(int id,int to) {
		for(User user:s_users) {
			if(user.getId()==id) {
				user.setHasAdminRights(to > 0);
				return user;
			}
		}
		
		return new QuickResponse("Customer not found");
	}

	public IResponse UpdateUser(int id, String username, String password,boolean giveAdminRights) {
		for(User user:s_users) {
			if(user.getId()==id) {
				user.setUserName(username != null ? username : user.getUserName());
				user.setPassWord(password != null ? password : user.getPassWord());
				user.setHasAdminRights(giveAdminRights);
				return user;
			}
		}
		
		return new QuickResponse("Customer not found");
	}

}
