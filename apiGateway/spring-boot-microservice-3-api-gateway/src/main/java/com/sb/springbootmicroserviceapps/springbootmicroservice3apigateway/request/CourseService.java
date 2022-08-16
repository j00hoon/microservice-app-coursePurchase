package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "course-service", path="/api/course", url="${course.service.url}",
			configuration = FeignConfiguration.class)
public interface CourseService 
{
	@PostMapping
	Object saveCourse(@RequestBody Object request);
	
	@DeleteMapping("/delete/{courseId}")
	void deleteCourse(@PathVariable("courseId") long courseId);
	
	@GetMapping
	List<Object> getAllCourses();
}
