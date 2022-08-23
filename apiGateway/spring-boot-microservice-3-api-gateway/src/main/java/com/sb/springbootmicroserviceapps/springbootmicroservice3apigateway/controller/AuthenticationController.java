package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service.AuthenticationService;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service.UserService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController 
{
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public User signUp(@RequestBody User user)
	{
		if(userService.findByUsername(user.getName()).isPresent())
		{
			return null;
		}// if
		
		return userService.save(user);
	}
	
	@PostMapping("/signin")
	@ResponseStatus(HttpStatus.OK)
	public User signIn(@RequestBody User user)
	{
//		if(userService.findByUsername(user.getUsername()).isPresent())
//		{
//			return userService.findByUsername(user.getUsername()).get();
//		}// if
//		
//		return null;
		
		return authenticationService.signInAndReturnJWT(user);
	}

}
