package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.Role;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
	
	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("Update User set role = :role where username = :username")
	void updateUserRole(@Param("username") String username, @Param("role") Role role);
	
	
}
