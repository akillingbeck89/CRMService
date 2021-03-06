package com.theam.CRMService.crmrestapi.models;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theam.CRMService.crmrestapi.exceptions.internal.CustomerNotFoundException;
import com.theam.CRMService.crmrestapi.exceptions.internal.DeletePhotoException;
import com.theam.CRMService.crmrestapi.exceptions.internal.NoPageContentException;
import com.theam.CRMService.crmrestapi.exceptions.internal.UploadPhotoException;
import com.theam.CRMService.crmrestapi.exceptions.internal.WrongInputException;
import com.theam.CRMService.crmrestapi.models.data.customers.Customer;
import com.theam.CRMService.crmrestapi.models.repository.CustomerRepository;
import com.theam.CRMService.crmrestapi.security.authentication.CRMUserDetailsService;
import com.theam.CRMService.crmrestapi.utils.FileStorageService;

@Component
public class CustomerDaoService {

	@Autowired
	private FileStorageService FileStorage;
	
	@Autowired
	private CustomerRepository Repository;

	public List<Customer> GetCustomers(int start,int stride){
		
		if(stride> 0) {
			Page<Customer> page = Repository.findAll(PageRequest.of(start, stride));
			
			if(page.hasContent()) {
				return page.getContent();
			}
			throw new NoPageContentException(String.format("No Customers Pages %d-%d", start,stride));
		}

		return Repository.findAll();
	
	}
	
	public void DeleteCustomerPhoto(int customerID){
		
		Optional<Customer> customer = Repository.findById(customerID);
		if(customer.isPresent()) {
			try {
				FileStorage.deleteFile(customer.get().getPhotoPath().toURI());
				customer.get().setPhotoPath(null);
				customer.get().setLastmodifiedby(CRMUserDetailsService.currentUserFromContext());
				Repository.save(customer.get());
				return;
			}
			catch(Exception e) {
				throw new DeletePhotoException(e);
			}
		}
		throw new CustomerNotFoundException(customerID);
	}
	public ResponseEntity<Object> UpdateCustomerPhoto(int customerID,MultipartFile file) {
		
		Optional<Customer> customer = Repository.findById(customerID);
		if(customer.isPresent()) {
			try {
				URI uri = FileStorage.store(file,"customers",String.valueOf(customerID));
				customer.get().setPhotoPath(uri.toURL());
				customer.get().setLastmodifiedby(CRMUserDetailsService.currentUserFromContext());
				Repository.save(customer.get());
				return ResponseEntity.created(uri).build();
			}
			catch(Exception e) {
				throw new UploadPhotoException(file.getOriginalFilename(),customerID,e);
			}
		}
		
		throw new CustomerNotFoundException(customerID);
	}
	public ResponseEntity<Customer> CreateCustomer(Customer customer) {
		try {
			
			customer.setCreatedby(CRMUserDetailsService.currentUserFromContext());
			Customer saved = Repository.save(customer);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		catch(IllegalArgumentException e) {
			throw new WrongInputException("Input was not a customer");
		}
	}

	public void DeleteCustomer(int id) {
		if(Repository.existsById(id)) {
			Repository.deleteById(id);
			return;
		}
		throw new CustomerNotFoundException(id);
	}
	
	public Customer GetCustomerDetails(int id) {
		Optional<Customer> customer = Repository.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		}
		
		throw new CustomerNotFoundException(id);
	}
	
	public Customer UpdateCustomer(int id, Customer pCustomer) {
		Optional<Customer> customer = Repository.findById(id);
		if(customer.isPresent()) {
			pCustomer.setId(customer.get().getId());
			pCustomer.setLastmodifiedby(CRMUserDetailsService.currentUserFromContext());
			return Repository.save(pCustomer);
		}
		throw new CustomerNotFoundException(id);
	}
}
