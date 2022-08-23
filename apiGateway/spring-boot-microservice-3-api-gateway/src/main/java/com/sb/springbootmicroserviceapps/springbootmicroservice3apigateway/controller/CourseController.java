package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.request.CourseService;

@RestController
@RequestMapping("/gateway/course")
public class CourseController 
{
	@Autowired
	private CourseService courseService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Object saveCourse(@RequestBody Object course)
	{
		return courseService.saveCourse(course);
	}
	
	
	@PatchMapping(path= "/partialUpdate/{courseId}", consumes = "application/json")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void partialUpdateCourse(@PathVariable("courseId") long courseId, @RequestBody Object course)
	{
		courseService.partialUpdateCourse(courseId, course);
	}
	 
	
	
	@DeleteMapping("/delete/{courseId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteCourse(@PathVariable("courseId") long courseId)
	{
		courseService.deleteCourse(courseId);
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Object> getAllCourses()
	{
		return courseService.getAllCourses();
	}
	
	
	

}
