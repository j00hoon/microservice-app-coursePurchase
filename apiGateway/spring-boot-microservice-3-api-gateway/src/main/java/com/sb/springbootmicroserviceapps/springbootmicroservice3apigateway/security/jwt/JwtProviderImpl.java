package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.security.UserPrincipal;
import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.util.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProviderImpl implements JwtProvider
{
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${app.jwt.expiration-in-ms}")
	private long JWT_EXPIRATION_IN_MS;
	
	@Override
	public String generateToken(UserPrincipal auth)
	{
		String authorities = auth.getAuthorities().stream()	
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","));
		
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		
		return Jwts.builder()
				.setSubject(auth.getUsername())
				.claim("roles", authorities)
				.claim("userId", auth.getId())
				//.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	
	@Override
	public Authentication getAuthentication(HttpServletRequest req)
	{
		Claims claims = extractClaims(req);
		
		if(claims == null)
		{
			return null;
		}// if
		
		String username = claims.getSubject();
		//long userId = claims.get("userId", long.class);
		Long userId = claims.get("userId", Long.class);
		
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertToAuthority)
				.collect(Collectors.toSet());
		
		UserDetails userDetails = UserPrincipal.builder()
				.username(username)
				//.authorities(authorities)
				.id(userId)
				.build();
		
		if(username == null)
		{
			return null;
		}// if
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}
	
	
	@Override
	public boolean isTokenValid(HttpServletRequest req)
	{
//		Claims claims = extractClaims(req);
//		
//		if(claims == null)
//		{
//			return false;
//		}// if
//		
//		if(claims.getExpiration().before(new Date()))
//		{
//			return false;
//		}// if
		
		return true;
	}
	
	
	
	
	private Claims extractClaims(HttpServletRequest req)
	{
//		String token = SecurityUtils.extractAuthTokeFromRequest(req);
//		
//		if(token == null)
//		{
//			return null;
//		}// if
		
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		String tmpToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqMDBob29uIiwicm9sZXMiOiJST0xFX0FETUlOIiwidXNlcklkIjo3fQ.IO6ZYFgZnA84_ZtZOjHv3BzghGbqWcBVxLp576_lwmqKl6trAbQFuYvRWKm_pHtKygDwfeU1GnFeOYe6EpXdyA";
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(tmpToken)
				.getBody();
	}
	
	
}
