package com.sb.springbootmicroserviceapps.controller;

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

import com.sb.springbootmicroserviceapps.entities.Course;
import com.sb.springbootmicroserviceapps.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseApiController 
{
	@Autowired
	private CourseService courseService;
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course saveCourse(@RequestBody Course course)
	{
		return courseService.saveCourse(course);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteCourse(@PathVariable("id") long id)
	{
		courseService.deleteCourse(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Course> findAllCourses()
	{
		return courseService.findAllCourses();
	}
	
	@PatchMapping(path = "/partialUpdate/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Course partialUpdateCourse(@PathVariable("id") long id, @RequestBody Course course)
	{
		Course updatedCourse = courseService.findCourseById(id);
		
		if(course.getId() != updatedCourse.getId())
		{
			updatedCourse.setId(course.getId());
		}// if
		if(course.getTitle() != null)
		{
			updatedCourse.setTitle(course.getTitle());
		}// if
		if(course.getSubtitle() != null)
		{
			updatedCourse.setSubtitle(course.getSubtitle());
		}// if
		if(course.getPrice() != null)
		{
			updatedCourse.setPrice(course.getPrice());
		}// if
		
		courseService.saveCourse(updatedCourse);
		
		return updatedCourse;
		
	}
	
	
	
}
