package com.smartshop.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public String saveUser(@RequestBody User user) {

		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/name/{name}")
	public User findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	@GetMapping("/role/{role}")
	public List<User> findByRole(@PathVariable String role) {
		return userService.findByRole(role);
	}

	@GetMapping("/emailId/{emailId}")
	public User findByEmailId(@PathVariable String emailId) {
		return userService.findByEmailId(emailId);
	}

	@PutMapping("/{id}")
	public User updateUserDetails(@PathVariable Long id, @RequestBody User userDetails) {

		return userService.updateUserDetails(id, userDetails);
	}

	@DeleteMapping("/{id}")
	public String deleteByUserId(@PathVariable long id) {
         userService.deleteByUserId(id);
		return "User is deleted";
	}

}
