package com.smartshop.user_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
 public User findByEmailId(String emailId);
 public List<User> findByRole(String role);
 public User findByName(String name);
}
