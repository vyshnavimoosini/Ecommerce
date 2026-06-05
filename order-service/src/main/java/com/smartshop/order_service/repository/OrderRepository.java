package com.smartshop.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.order_service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	
	List<Order> findByUserId(long userId);
	List<Order> findByTrackingId(String  trackingId);
	List<Order> findByStatus(String status);

}
