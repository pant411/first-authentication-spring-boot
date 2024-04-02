package com.project.authapi.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.authapi.auth.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

 public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
  this.userDetailsService = userDetailsService;
  this.jwtService = jwtService;
 }

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request, 
    @NonNull HttpServletResponse response, 
    @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwtToken = authorizationHeader.substring(7);
      try {
        username = jwtService.extractUsername(jwtToken);
      } catch (Exception e) {
        // Handle token extraction/validation errors
        System.out.println("Error extracting username from token: " + e.getMessage());
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (jwtService.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}