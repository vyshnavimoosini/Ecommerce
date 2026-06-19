package com.smartshop.product_service.controller;

import java.math.BigDecimal;
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

import com.smartshop.product_service.entity.Product;
import com.smartshop.product_service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public String saveProduct(@Valid @RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping("/{productId}")
	public Product getByProductId(@PathVariable long productId) {

		return productService.getByProductId(productId);
	}

	@GetMapping("/price-range")
	public List<Product> findByProductPriceBetween(@RequestParam BigDecimal minPrice,
			@RequestParam BigDecimal maxPrice) {

		return productService.findByProductPriceBetween(minPrice, maxPrice);
	}

	@GetMapping("/search")
	public List<Product> findByProductNameContainingIgnoreCase(@RequestParam String name) {
		return productService.findByProductNameContainingIgnoreCase(name);
	}

	@GetMapping("/stock/{stock}")
	public List<Product> findByProductStockGreaterThan(@PathVariable Integer stock) {
		return productService.findByProductStockGreaterThan(stock);
	}

	@GetMapping("/category/{category}")
	public List<Product> findByCategory(@PathVariable String category) {

		return productService.findByCategory(category);
	}

	@GetMapping
	public List<Product> findAll() {
		return productService.findAll();
	}

	@PutMapping("/{productId}")
	public Product updateProduct(@PathVariable long id, @Valid @RequestBody Product productDetails) {

		return productService.updateProduct(id, productDetails);

	}

	@DeleteMapping("/{productId}")
	public String deleteByProductId(@PathVariable long productId) {

		productService.deleteByProductId(productId);

		return "Product is deleted";

	}

}
