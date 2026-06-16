package com.smartshop.user_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name;
	
	@Column(unique = true)
	String emailId;
	String password;
	String role;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	

}
