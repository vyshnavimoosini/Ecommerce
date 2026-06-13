package com.smartshop.chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public ChatbotService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
            .defaultSystem("You are a helpful shopping assistant for SmartShop. Use the provided product information to answer customer questions. If no relevant products are found, say so politely.")
            .build();
        this.vectorStore = vectorStore;
    }

    public String ask(String question) {
        List<Document> relevantDocs = vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(question)
                .topK(3)
                .build()
        );

        String context = relevantDocs.stream()
            .map(Document::getText)
            .collect(Collectors.joining("\n"));

        String prompt = String.format(
            "Based on the following product information:\n%s\n\nCustomer question: %s",
            context.isEmpty() ? "No product information available." : context,
            question
        );

        return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
    }
}
