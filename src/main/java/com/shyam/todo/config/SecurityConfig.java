package com.shyam.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    //    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetailsManager userDetailsManager = new InMemoryUserDetailManager();
//        userDetailsManager.createUser(User
//                .withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER"
//                .build());
//        userDetailsManager.createUser(User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN"
//                .build());
//                return userDetailsManager;
//    }
    @Bean
    InMemoryUserDetailsManager users(){
        return new InMemoryUserDetailsManager(
                User.withUsername("shyam")
                        .password("{noop}password")
                        .roles("ADMIN")
                        .build()
        );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth-> auth
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .build();
    }
}
