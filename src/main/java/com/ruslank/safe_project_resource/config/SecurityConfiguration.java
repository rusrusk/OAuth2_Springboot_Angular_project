package com.ruslank.safe_project_resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Value("${jwkUri}")
    private String jwkUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.oauth2ResourceServer(
                j -> j.jwt().jwkSetUri(jwkUri)
        ).authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}
