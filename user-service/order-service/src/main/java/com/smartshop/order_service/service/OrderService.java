package com.smartshop.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.order_service.entity.Order;
import com.smartshop.order_service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public List<Order> getByUserId(long userId) {

		return orderRepository.findByUserId(userId);

	}

	public String createOrder(Order order) {

		orderRepository.save(order);

		return "Order is saved";

	}

	public List<Order> findByTrackingId(String trackingId) {

		return orderRepository.findByTrackingId(trackingId);
	}

	public Order getByOrderId(long orderId) {

		return orderRepository.findById(orderId).orElse(null);

	}

	public List<Order> getAll() {

		return orderRepository.findAll();

	}

	public List<Order> findByStatus(String status) {

		return orderRepository.findByStatus(status);
	}

	public Order updateOrder(long orderId, Order orderDetails) {

		Order order = orderRepository.findById(orderId).orElse(null);

		order.setStatus(orderDetails.getStatus());
		order.setTrackingId(orderDetails.getTrackingId());
		order.setShippingAddress(orderDetails.getShippingAddress());

		return orderRepository.save(order);

	}
    
	public Order cancelOrder(long orderId) {

		Order order = orderRepository.findById(orderId).orElse(null);

		order.setStatus("CANCELLED");

		return orderRepository.save(order);

	}
}
