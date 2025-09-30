package com.app.service;

import org.springframework.stereotype.Service;

import com.app.entities.User;

public interface UserService {

	User getUserByEmail(String email);

	User login(String email, String password);

	
}
