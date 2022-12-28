package com.student.studentManagement.config;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Service.DatabaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DatabaseUserService databaseUserService;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests()
//                                .requestMatchers("/api/**")
                                .requestMatchers("/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                // tell spring to keep our session creation policy stateless
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                // tell spring to user our authentication provider
                                .authenticationProvider(authenticationProvider())
                                // telling spring to add out custom filter jwtAuth filter before UsernamePasswordAuthenticationFilter
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // telling spring to user our UserDetailService implementation
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
                return databaseUserService.findByEmail(userEmail);
            }
        };
    }
}
