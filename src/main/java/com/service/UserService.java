package com.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.entity.User;
import com.repository.IUserRepository;

@Named
public class UserService implements Serializable, IUserService{

	/**
	 * 
	 **/
	private static final long serialVersionUID = 1L;

	@Inject
	private IUserRepository userRepository;
	
	@Override
	public User login(User u) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.login(u);
	}

	
}
