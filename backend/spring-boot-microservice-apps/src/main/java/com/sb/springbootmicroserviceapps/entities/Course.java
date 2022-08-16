package com.sb.springbootmicroserviceapps.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Course")
public class Course 
{
	@Id
	private long id;
	private String title;
	private String subtitle;
	private Double price;
	private LocalDate createdTime;
	
}
