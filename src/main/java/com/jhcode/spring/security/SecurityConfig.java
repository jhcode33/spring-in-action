package com.jhcode.spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                   .httpBasic(httpBasic -> httpBasic.disable()) // Basic 로그인이 아닌 Form 로그인 방식 사용
                   .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/design", "/orders")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/", "/**")).permitAll()
                        .anyRequest().permitAll())

                .formLogin(formLogin -> formLogin.loginPage("/login"))
                .build();
    }
}
