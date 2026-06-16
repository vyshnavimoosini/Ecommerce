package com.smartshop.user_service.security;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String emailId;
	private String password;

}
