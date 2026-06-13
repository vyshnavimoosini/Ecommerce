package com.smartshop.product_service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.product_service.entity.Product;
import com.smartshop.product_service.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public String saveProduct(Product product) {

		productRepository.save(product);

		return "Product is saved";

	}

	public Product getByProductId(long productId) {

		return productRepository.findById(productId).orElse(null);
	}

	public List<Product> findByProductPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {

		return productRepository.findByProductPriceBetween(minPrice, maxPrice);
	}

	public List<Product> findByProductNameContainingIgnoreCase(String name) {
		return productRepository.findByProductNameContainingIgnoreCase(name);
	}

	public List<Product> findByProductStockGreaterThan(Integer stock) {
		return productRepository.findByProductStockGreaterThan(stock);
	}
	
	public List<Product> findByCategory(String category){
		
		return productRepository.findByProductCategory(category);
	}

	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(long productId, Product productDetails) {
		
		Product product = productRepository.findById(productId).orElse(null);

		product.setProductName(productDetails.getProductName());
		product.setProductPrice(productDetails.getProductPrice());
		product.setProductDescription(productDetails.getProductDescription());
		product.setProductStock(productDetails.getProductStock());
		product.setProductCategory(productDetails.getProductCategory());

		return productRepository.save(product);

		
	}
	
	public String deleteByProductId(long productId) {

		productRepository.deleteById(productId);

		return "Product is deleted";

	}

};
