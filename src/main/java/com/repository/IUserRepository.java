package com.repository;

import com.entity.User;

public interface IUserRepository {
	
	User login(User u) throws Exception;

}
