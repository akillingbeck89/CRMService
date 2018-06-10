package com.theam.CRMService.crmrestapi.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theam.CRMService.crmrestapi.models.data.customers.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
