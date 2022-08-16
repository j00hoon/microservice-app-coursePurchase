package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique=true, nullable=false, length=100)
	private String username;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "create_time", nullable=false)
	private LocalDateTime createdTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable=false)
	private Role role;
	
	@Transient
	private String token;
	
}
