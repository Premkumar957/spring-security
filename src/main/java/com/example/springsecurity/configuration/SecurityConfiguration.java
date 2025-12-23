package com.example.springsecurity.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1.CSRF Disable
        //   SAME SITE STRICT
        //   SESSION - STATELES

        // Spring security will not expect csrf token
        http.csrf(customizer -> customizer.disable());
        //Every HTTP request must come from an authenticated user
        http.authorizeHttpRequests(request -> request
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")
            .requestMatchers("/public/**").permitAll()
            .anyRequest().authenticated());
        // http.formLogin(Customizer.withDefaults());

        //for login for API client
        http.httpBasic(Customizer.withDefaults());

        //Every time you get the new session ID
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));  // For plain text - NoOpPasswordEncoder.getInstance();
        // provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // @Bean
    // public UserDetailsService userDetailsService() {

    //     UserDetails user1 = User
    //             .withDefaultPasswordEncoder()
    //             .username("rohit")
    //             .password("1357")
    //             .roles("USER")
    //             .build();
            
    //     UserDetails user2 = User
    //             .withDefaultPasswordEncoder()
    //             .username("kohli")
    //             .password("2000")
    //             .roles("USER")
    //             .build();
        
    //     UserDetails user3 = User
    //             .withDefaultPasswordEncoder()
    //             .username("pant")
    //             .password("2100")
    //             .roles("USER")
    //             .build();

    //     return new InMemoryUserDetailsManager(user1, user2, user3);
    // }
    
}
