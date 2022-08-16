package com.sb.springbootmicroserviceapps.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "TestIDGenerator")
public class TestIDGenerator 
{
	@Id
	private long id;
	
	private String name;
}
