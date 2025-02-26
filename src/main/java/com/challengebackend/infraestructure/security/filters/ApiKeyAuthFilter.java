package com.challengebackend.infraestructure.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final String API_KEY = "mi-api-key-secreta"; // API Key estática
    private static final Logger logger = Logger.getLogger(ApiKeyAuthFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-KEY");

        logger.info("API Key recibida: " + apiKey); // Log para depuración

        if (apiKey != null && apiKey.equals(API_KEY)) {
            // Si la API Key es válida, permite el acceso
            filterChain.doFilter(request, response);
        } else {
            // Si la API Key es inválida o no está presente, devuelve un error 403 (Prohibido)
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("API Key inválida o no proporcionada");
        }
    }
}