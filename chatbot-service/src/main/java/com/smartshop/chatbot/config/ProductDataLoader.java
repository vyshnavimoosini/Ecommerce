package com.smartshop.chatbot.config;

import com.smartshop.chatbot.dto.ProductDTO;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ProductDataLoader implements CommandLineRunner {

    private final VectorStore vectorStore;

    public ProductDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ProductDTO[] products = restTemplate.getForObject(
                "http://localhost:8082/api/v1/products", ProductDTO[].class);

            if (products != null && products.length > 0) {
                List<Document> documents = new ArrayList<>();
                for (ProductDTO product : products) {
                    String content = String.format(
                        "Product: %s, Description: %s, Price: %.2f, Category: %s, Stock: %d",
                        product.getProductName() != null ? product.getProductName() : "Unknown",
                        product.getProductDescription() != null ? product.getProductDescription() : "No description",
                        product.getProductPrice() != null ? product.getProductPrice() : 0.0,
                        product.getProductCategory() != null ? product.getProductCategory() : "Uncategorized",
                        product.getProductStock() != null ? product.getProductStock() : 0
                    );
                    Map<String, Object> metadata = new HashMap<>();
                    if (product.getProductId() != null) {
                        metadata.put("productId", product.getProductId());
                    }
                    if (product.getProductCategory() != null) {
                        metadata.put("category", product.getProductCategory());
                    }
                    documents.add(new Document(content, metadata));
                }
                vectorStore.add(documents);
                System.out.println("Loaded " + documents.size() + " products into vector store");
            }
        } catch (Exception e) {
            System.out.println("Could not load products: " + e.getMessage());
            System.out.println("Chatbot will work without product context");
        }
    }
}
