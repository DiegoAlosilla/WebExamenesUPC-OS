package com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.entity.Course;

@Named
public class CourseRepository implements Serializable, ICourseRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="VisorE")
	private EntityManager em;
	
	
	@Transactional
	@Override
	public Course save(Course c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public Course update(Course c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll() throws Exception {
		List<Course> courses = new ArrayList<>();
		Query query = em.createQuery("SELECT c FROM Course c");
		courses = (List<Course>) query.getResultList();
		return courses;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll(String name) throws Exception {
		List<Course> courses = new ArrayList<>();
		Query query = em.createQuery("SELECT c FROM Course c WHERE c.course LIKE ?1");
		query.setParameter(1, '%'+name+'%');
		courses = (List<Course>) query.getResultList();
		return courses;
	}
	
	

}
