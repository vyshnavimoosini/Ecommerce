package com.smartshop.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public String saveUser(User user) {

		userRepository.save(user);

		return "saved Sucessfully";

	}

}
