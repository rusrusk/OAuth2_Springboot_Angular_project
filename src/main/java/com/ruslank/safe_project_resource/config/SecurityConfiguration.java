package com.ruslank.safe_project_resource.config;

//import com.ruslank.safe_project_resource.cors.CustomCorsHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

//    private final CustomCorsHandler customCorsHandler;

//    @Value("${jwkUri}")
//    private String jwkUri;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        customCorsHandler.corsHandler(httpSecurity);
        httpSecurity.cors(
                c -> {
                    CorsConfigurationSource configurationSource = source -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
                        corsConfiguration.setAllowedHeaders(List.of("*"));
                        corsConfiguration.setAllowedMethods(List.of("*"));
                        return  corsConfiguration;
                    };
                    c.configurationSource(configurationSource);
                }
        );
        return httpSecurity.oauth2ResourceServer(
                j -> j.jwt().jwkSetUri("http://localhost:8080/oauth2/jwks")
        ).authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .build();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders("Access-Control-Allow-Origin") // add this line
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
