package com.theam.CRMService.crmrestapi.models;

import java.io.IOException;
import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.models.data.customers.Customer;
import com.theam.CRMService.crmrestapi.utils.FileStorageService;

@Repository
@Transactional
@Component
public class CustomerDaoService {
	
	@Autowired
	private FileStorageService FileStorage;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@PersistenceContext
	private EntityManager m_entityManager;

	public ResponseEntity<Object> GetCustomers(int start,int stride){
		try {
			Query query = m_entityManager.createQuery("SELECT customer FROM Customer customer").setFirstResult(start).setMaxResults(stride);
			return ResponseEntity.ok(query.getResultList());
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	public ResponseEntity<Object> DeleteCustomerPhoto(long customerID){
			try {
				Customer customer = (Customer)m_entityManager.find(Customer.class, customerID);
				try {
					FileStorage.deleteFile(customer.getPhotoPath().toURI());
					customer.setPhotoPath(null);
					return ResponseEntity.accepted().build();
				}
				catch(Exception e) {
					return ResponseEntity.badRequest().build();
				}
			}
			catch(Exception e) {
				return ResponseEntity.badRequest().build();
			}

	}
	public ResponseEntity<Object> UpdateCustomerPhoto(long customerID,MultipartFile file) {
		
		try {
			Customer customer = (Customer)m_entityManager.find(Customer.class, customerID);
			try {
				URI uri = FileStorage.store(file,"customers",String.valueOf(customerID));
				customer.setPhotoPath(uri.toURL());
				return ResponseEntity.created(uri).build();
			}
			catch(IOException e) {
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
			}
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	public ResponseEntity<Object> CreateCustomer(Customer customer) {
		try {
			m_entityManager.persist(customer);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> DeleteCustomer(long id) {
		
		try {
			Customer customer = (Customer)m_entityManager.find(Customer.class, id);
			m_entityManager.remove(customer);
			return ResponseEntity.accepted().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	
	}
	
	public ResponseEntity<Object> GetCustomerDetails(long id) {
		try {
			Customer customer = (Customer)m_entityManager.find(Customer.class, id);
			return ResponseEntity.ok(customer);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> UpdateCustomer(long id, String name, String surname) {

		try {
			Customer customer= (Customer)m_entityManager.find(Customer.class, id);
			customer.setForeName(customer != null ? name : customer.getForeName());
			customer.setSurName(customer != null ? surname : customer.getSurName());
			return ResponseEntity.ok().body(customer);
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
