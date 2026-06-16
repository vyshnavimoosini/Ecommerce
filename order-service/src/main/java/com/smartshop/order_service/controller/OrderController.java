package com.smartshop.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.order_service.entity.Order;
import com.smartshop.order_service.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping
	public String createOrder(@RequestBody Order order) {

		orderService.createOrder(order);

		return "Order is saved";

	}
	
	@GetMapping("/userId/{userId}")
	public List<Order> getByUserId(@PathVariable long userId) {

		return orderService.getByUserId(userId);

	}
	
	@GetMapping("/trackingId/{trackingId}")
	public List<Order> findByTrackingId(@PathVariable String trackingId) {

		return orderService.findByTrackingId(trackingId);
	}

	@GetMapping("/{orderId}")
	public Order getByOrderId(@PathVariable long orderId) {

		return orderService.getByOrderId(orderId);

	}
	
	@GetMapping
	public List<Order> getAll() {

		return orderService.getAll();

	}
	
	@GetMapping("/status/{status}")
	public List<Order> findByStatus(@PathVariable String status) {

		return orderService.findByStatus(status);
	}
    
	@PutMapping("/{orderId}")
	public Order updateOrder(@PathVariable long orderId, @RequestBody Order orderDetails) {

		
		return orderService.updateOrder(orderId,orderDetails);

	}
	
	@PutMapping("/cancel/{orderId}")
	public Order cancelOrder(@PathVariable long orderId) {

		
		return orderService.cancelOrder(orderId);

	}

    @DeleteMapping("/{orderId}")
	public String deleteOrder(@PathVariable Long orderId) {
		orderService.deleteOrder(orderId);
		
		return "Order Deleted";
	}
}
