package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.Role;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.UserPrincipal;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<User> findAll()
	{
		return userService.findAll();
	}	
	
	
	@PutMapping("/update/{role}")
	@ResponseStatus(HttpStatus.OK)
	public boolean updateUserRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role)
	{
		userService.updateUserRole(userPrincipal.getUsername(), role);
		
		return true;
	}
	
	@PatchMapping(path = "/partialUpdate/{userId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public User partialUpdateUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("userId") Long id,
									@RequestBody User newUser)
	{
		User user = userService.findByUsername(userPrincipal.getUsername()).get();
		
		if(newUser.getName() != null)
		{
			user.setName(newUser.getName());
		}// if
		if(newUser.getPassword() != null)
		{
			user.setName(newUser.getPassword());
		}// if
		if(newUser.getUsername() != null)
		{
			user.setUsername(newUser.getUsername());
		}// if
		
		return userService.save(user);
	}

}
