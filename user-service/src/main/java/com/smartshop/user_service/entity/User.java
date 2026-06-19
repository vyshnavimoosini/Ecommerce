package com.smartshop.user_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	long id;
	@NotBlank(message = "Name is required")
	String name;
	
	@Column(unique = true)
	@NotBlank(message = "Email is required")
	String emailId;
	@NotBlank(message = "Password is required")
	String password;
	@NotBlank(message = "Role is required")
	String role;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	

}
