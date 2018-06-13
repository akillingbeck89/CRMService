package com.theam.CRMService.crmrestapi.models.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theam.CRMService.crmrestapi.models.data.Users.User;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer> 
{
	User findByUsername(String username);
}
