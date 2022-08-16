package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.Role;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public List<User> findAll()
	{
		return userRepo.findAll();
	}
	
	
	public User save(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setCreatedTime(LocalDateTime.now());
		
		return userRepo.save(user);
	}
	
	public Optional<User> findByUsername(String username)
	{
		return userRepo.findByUsername(username);
	}
	
	@Transactional
	public void updateUserRole(String username, Role role)
	{
		userRepo.updateUserRole(username, role);
	}
}
