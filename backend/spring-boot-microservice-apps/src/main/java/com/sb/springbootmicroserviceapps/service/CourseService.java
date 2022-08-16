package com.sb.springbootmicroserviceapps.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.springbootmicroserviceapps.entities.Course;
import com.sb.springbootmicroserviceapps.repository.CourseRepository;

@Service
public class CourseService 
{
	@Autowired
	private CourseRepository courseRepo;
	
	public Course saveCourse(Course course)
	{
		course.setCreatedTime(LocalDate.now());
		
		return courseRepo.save(course);
	}
	
	public void deleteCourse(long courseId)
	{
		courseRepo.deleteById(courseId);
	}
	
	public List<Course> findAllCourses()
	{
		return courseRepo.findAll();
	}

	public Course findCourseById(long id) 
	{
		return courseRepo.findById(id).get();
	}
	
	

}
