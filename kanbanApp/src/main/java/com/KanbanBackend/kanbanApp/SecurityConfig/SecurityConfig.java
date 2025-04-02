package com.KanbanBackend.kanbanApp.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/public/**", "/", "/home").permitAll()  // Public endpoints
                            .anyRequest().authenticated();  // All other requests need authentication
                })
                .formLogin(form -> form
                        .loginPage("http://localhost:3000/Login")  // Your custom login page
                        .permitAll()
                );

        return http.build();
    }

    // Configure your authentication provider here if needed
}