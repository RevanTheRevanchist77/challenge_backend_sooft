package com.challengebackend.infraestructure.config;

import com.challengebackend.infraestructure.security.filters.ApiKeyAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable() // Deshabilita CSRF (no es necesario para APIs REST)
	        .addFilterBefore(new ApiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class) // Agrega el filtro de API Key
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/empresas/**").authenticated() // Protege todos los endpoints de empresas
	            .requestMatchers("/transferencias/**").authenticated() // Protege todos los endpoints de transferencias
	            .anyRequest().permitAll() // El resto de endpoints son públicos
	        );

	    return http.build();
	}
}