package com.KanbanBackend.kanbanApp.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/public/**", "/", "/home", "/api", "/api/sign-up", "/api/**")
                            .permitAll()  // Public endpoints
                            .anyRequest().authenticated();  // All other requests need authentication
                })
                .formLogin(form -> form
                        .loginPage("/http://localhost:3000/Login")  // Use a relative path instead of absolute URL
                        .permitAll()
                );

        return http.build();
    }

    // Configure your authentication provider here if needed
}