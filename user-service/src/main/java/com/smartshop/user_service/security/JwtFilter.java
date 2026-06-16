package com.smartshop.user_service.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	

    @Autowired
    private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		    

		        // Step 1: Get the "Authorization" header from request
		        String authHeader = request.getHeader("Authorization"); 

		        // Step 2: Check if header exists and starts with "Bearer "
		        if (authHeader != null && authHeader.startsWith("Bearer ")) {

		            // Step 3: Extract token (remove "Bearer " prefix — 7 characters)
		            String token = authHeader.substring(7); 

		            // Step 4: Validate the token
		            if (jwtUtil.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {

		                // Step 5: Extract email and role from token
		                String email = jwtUtil.extractEmail(token);
		                String role = jwtUtil.extractRole(token);

		                // Step 6: Create authentication object and set in SecurityContext
		                UsernamePasswordAuthenticationToken authToken =
		                    new UsernamePasswordAuthenticationToken(
		                        email, null,
		                        Collections.singletonList(new SimpleGrantedAuthority(role))
		                    );
		                SecurityContextHolder.getContext().setAuthentication(authToken);
		            }
		        }

		        // Step 7: Always pass request to next filter
		        filterChain.doFilter(request, response);
		    }
		
		
	}


