package com.smartshop.user_service.service;

import java.util.List;
import java.util.Optional;

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

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	public List<User> findByRole(String role){
		return userRepository.findByRole(role);
	}
	
	 public User findByEmailId(String emailId) {
		 return userRepository.findByEmailId(emailId);
	 }
	 
	 public User updateUserDetails(Long id, User userDetails) {
		 
		 User user= userRepository.findById(id).orElse(null);
		 
		 user.setName(userDetails.getName());
		 user.setRole(userDetails.getRole());
		 user.setEmailId(userDetails.getEmailId());
		 
		 
		 return userRepository.save(user);
	 }
	 
	 public String deleteByUserId(long id) {

			userRepository.deleteById(id);

			return "User is deleted";
	 }

}
