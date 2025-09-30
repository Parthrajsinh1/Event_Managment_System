package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.controller.UserController;
import com.app.dao.UserDao;
import com.app.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User getUserByEmail(String email) {

		return userDao.findByEmail(email);
	}

	@Override
	public User login(String email, String password) {
		User user = userDao.findByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}

		return user;
	}

}
