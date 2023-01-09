package com.example.vote.global.security;

import com.example.vote.domain.member.service.MemberService;
import com.example.vote.global.exception.CustomAuthenticationEntryPoint;
import com.example.vote.global.jwt.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final MemberService memberService;
    private final String key;

    public SecurityConfig(final MemberService memberService,
                          @Value("${jwt.secret-key}") final String key) {
        this.memberService = memberService;
        this.key = key;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .mvcMatchers(
                                "/api/*/users/joins",
                                "/api/*/users/login").permitAll()
                        .mvcMatchers("/api/*/users/joins",
                                "/api/*/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(key, memberService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .build();
    }

}
