package com.smartshop.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userservice;
	
	@PostMapping
	public String saveUser(@RequestBody User user) {

		return userservice.saveUser(user);
	}
}

