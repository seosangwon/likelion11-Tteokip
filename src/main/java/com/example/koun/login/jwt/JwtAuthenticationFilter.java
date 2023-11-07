package com.example.koun.login.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAppropriateRequestForFilter(request)) {
            try {
                String token = jwtUtil.resolveToken(request);
                Authentication authentication = jwtUtil.getAuthentication(token);
                System.out.println("Authentication Principal: " + authentication.getPrincipal());
                System.out.println("Authentication Authorities: " + authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException e) {
                System.err.println("JWT verification failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("JWT verification failed: " + e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAppropriateRequestForFilter(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authHeader);
        return (authHeader != null && authHeader.startsWith("Bearer "));
    }

}
