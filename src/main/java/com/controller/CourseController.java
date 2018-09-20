package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.entity.Course;
import com.service.ICourseService;

@Named
@SessionScoped
public class CourseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ICourseService courseService;

	private List<Course> courses;
	private Course course;
	private Course courseSelection;

	@PostConstruct
	public void init() {
		courses = new ArrayList<>();
		course = new Course();
		courseSelection = new Course();

		this.getAllCourses();
	}

	// Action
	public void cleanCourse() {
		course = new Course();
	}

	public String newCourse() {
		this.cleanCourse();
		return "index?faces-redirect=true";
	}

	public String editCourse() {
		String rpta = "";

		if (this.courseSelection != null && this.courseSelection.getCourse().length() >= 0) {
			this.course = this.courseSelection;
			rpta = "index?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso no seleccionado", "Curso no seleccionado"));
		}

		return rpta;
	}

	// Controller-Service
	public void getAllCourses() {
		try {
			courses = courseService.getAllCourses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reportCourseByName() {
		try {
			courses = courseService.getAllCoursesByName(course.getCourse());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void saveCourse() {
		try {
			courseService.saveCourse(course);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso registrado", "Curso registrado"));
			this.getAllCourses();
			this.cleanCourse();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage()));
		}
	}

	public void updateCourse() {
		try {
			courseService.updateCourse(course);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso actualizado", "Curso actualizado"));
			this.cleanCourse();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage()));
		}
	}

	// get set

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourseSelection() {
		return courseSelection;
	}

	public void setCourseSelection(Course courseSelection) {
		this.courseSelection = courseSelection;
	}

}
