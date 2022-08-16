package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.UserPrincipal;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.jwt.JwtProvider;

@Service
public class AuthenticationService 
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	public User signInAndReturnJWT(User signInReq)
	{
		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInReq.getUsername(), signInReq.getPassword()));
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtProvider.generateToken(userPrincipal);
		
		User signInUser = userPrincipal.getUser();
		signInUser.setToken(jwt);
		
		return signInUser;
		
	}

}
