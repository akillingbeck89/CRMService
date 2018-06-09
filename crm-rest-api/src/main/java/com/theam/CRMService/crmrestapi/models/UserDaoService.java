package com.theam.CRMService.crmrestapi.models;

import java.net.URI;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.models.data.Users.User;

@Repository
@Transactional
@Component
public class UserDaoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@PersistenceContext
	private EntityManager m_entityManager;

	public ResponseEntity<Object> CreateUser(User puser) {
		
		try {
			m_entityManager.persist(puser);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(puser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	public ResponseEntity<Object> GetUsers(int start,int stride){

		try {
			Query query = m_entityManager.createQuery("SELECT user FROM User user").setFirstResult(start).setMaxResults(stride);
			return ResponseEntity.ok(query.getResultList());
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	public ResponseEntity<Object> DeleteUser(long id) {
		
		try {
			User user = (User)m_entityManager.find(User.class, id);
			m_entityManager.remove(user);
			return ResponseEntity.accepted().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	
	}

	public ResponseEntity<Object> UpdateUser(long id, String username, String password,boolean giveAdminRights) {

		try {
			User user= (User)m_entityManager.find(User.class, id);
			user.setUserName(username != null ? username : user.getUserName());
			user.setPassWord(password != null ? password : user.getPassWord());
			user.setHasAdminRights(giveAdminRights);
			return ResponseEntity.ok().body(user);
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
