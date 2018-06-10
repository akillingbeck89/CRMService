package com.theam.CRMService.crmrestapi.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theam.CRMService.crmrestapi.models.data.Users.User;

public interface UserRepository extends JpaRepository<User,Long> 
{

}
