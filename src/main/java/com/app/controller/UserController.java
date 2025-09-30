package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.entities.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/event/auth")
public class UserController {

    private final EventController eventController;

	public UserController(EventController eventController) {
		System.out.println("in user controller ");
		this.eventController = eventController;
	}

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

//		Map<String, String> response = new HashMap<>();
//
//		User user = userService.getUserByEmail(request.getEmail());
//
//		if (user == null || !user.getPassword().equals(request.getPassword())) {
//			response.put("Invalid ", " Email or Password !!");
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//		}
//
//		response.put("Login ", "Success ! ");
//		response.put("Role ", user.getRole().getRoleName().name());
//		return ResponseEntity.ok(response);
		
//		User user = userService.getUserByEmail(request.getEmail());
//		
//		if(user == null || !user.getPassword().equals(request.getPassword()) ) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credintial !");
//		}
//		
//		return ResponseEntity.ok("Login success for "+ user.getRole().getRoleName());
		
	User user = userService.login(request.getEmail(), request.getPassword());
	if(user==null) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not valid !!");
	}
	
	return ResponseEntity.ok("Login success for "+user.getRole().getRoleName());
	
	}
}