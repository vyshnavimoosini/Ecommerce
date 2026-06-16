package com.smartshop.gateway.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

// @Component
public class JwtGatewayFilter implements GlobalFilter, Ordered{

	@Autowired
    private JwtUtil jwtUtil;

    private final List<String> publicPaths = List.of(
        "/auth/register",
        "/auth/login"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // Step 1: Skip filter for public paths
        for (String publicPath : publicPaths) {
            if (path.contains(publicPath)) {
                return chain.filter(exchange);
            }
        }
        
     // Step 2: Get Authorization header
        String authHeader = request.getHeaders().getFirst("Authorization");

        // Step 3: Check if header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Step 4: Extract and validate token
        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token) ) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Step 5: Token is valid, continue to service
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
