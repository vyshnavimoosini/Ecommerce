package com.smartshop.product_service.repo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.product_service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategory(String category);

    List<Product> findByProductNameContainingIgnoreCase(String name);

    List<Product> findByProductPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByProductStockGreaterThan(Integer stock);


} 
	

