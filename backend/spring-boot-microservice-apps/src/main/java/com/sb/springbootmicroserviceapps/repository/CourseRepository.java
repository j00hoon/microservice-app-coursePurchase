package com.sb.springbootmicroserviceapps.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sb.springbootmicroserviceapps.entities.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> 
{
	
}
