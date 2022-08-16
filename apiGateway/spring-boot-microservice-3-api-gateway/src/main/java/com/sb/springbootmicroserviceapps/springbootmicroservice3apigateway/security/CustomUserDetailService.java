package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities.User;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.service.UserService;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.util.SecurityUtils;

@Service
public class CustomUserDetailService implements UserDetailsService
{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		
		// User can have multiple roles, so need to use "Set"
		Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));
		
		
		return UserPrincipal.builder()
				.user(user)
				.id(user.getId())
				.username(username)
				.password(user.getPassword())
				.authorities(authorities)
				.build();
		
		
	}
	
	
}
