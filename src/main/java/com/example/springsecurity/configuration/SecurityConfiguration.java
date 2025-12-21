package com.example.springsecurity.configuration;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1.CSRF Disable
        //   SAME SITE STRICT
        //   SESSION - STATELESS
        // 2.Functional interface and lambda expression

        http.csrf(customizer -> customizer.disable());
        //authenticate every resource request
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());

        //for login for API client
        http.httpBasic(Customizer.withDefaults());

        //Every time you get same session ID
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
    
}
