package com.service;

import java.util.List;

import com.entity.Course;



public interface ICourseService {
	public Course saveCourse(Course c) throws Exception;
	public Course updateCourse(Course c) throws Exception;
	public List<Course> getAllCourses()throws Exception;
	
	public List<Course> getAllCoursesByName(String name) throws Exception;

}
