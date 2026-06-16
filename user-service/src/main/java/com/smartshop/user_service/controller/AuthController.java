package com.smartshop.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.security.AuthRequest;
import com.smartshop.user_service.security.JwtUtil;
import com.smartshop.user_service.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	
   @Autowired
   private JwtUtil jwtUtil;	
   
   private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   
   @PostMapping("/register")
   public String userRegister(@RequestBody User user) {
	   User existing = userService.findByEmailId(user.getEmailId());
       if (existing != null) {
           throw new RuntimeException("Email already registered");
       }
	  user.setPassword(encoder.encode(user.getPassword()));
	  userService.saveUser(user);
	  
	   return "User Registered";
	   
   }
   
   
   
   @PostMapping("/login")
   public String userLogin(@RequestBody AuthRequest authRequest) {
	   
	   User user=userService.findByEmailId(authRequest.getEmailId());
	   
	   if(user==null) {
		   throw new RuntimeException("User Not Found");
	   }
	   if(!encoder.matches(authRequest.getPassword(),user.getPassword())) {
		   throw new RuntimeException("Invalid Password");
	   }
	   
	   return jwtUtil.generateToken(user.getEmailId(), user.getRole());
	   
   }

}
