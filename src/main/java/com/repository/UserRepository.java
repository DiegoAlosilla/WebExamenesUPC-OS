package com.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entity.User;

@Named
public class UserRepository implements Serializable,IUserRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "VisorE")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public User login(User u) throws Exception {
		
		User user=null;// = new User();
		Query query = em.createQuery("FROM User u WHERE u.tiu = ?1 and u.password = ?2");
		
		query.setParameter(1, u.getTiu());
		query.setParameter(2, u.getPassword());

		List<User> users = query.getResultList();
		if (!users.isEmpty()) 
			user = users.get(0);
		
		return user;
	}

}
