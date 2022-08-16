package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.UserPrincipal;

public interface JwtProvider 
{
	public String generateToken(UserPrincipal auth);
	
	public Authentication getAuthentication(HttpServletRequest req);
	
	public boolean isTokenValid(HttpServletRequest req);
}
