//package com.ruslank.safe_project.cors;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.List;
//
//@Component
//public class CustomCorsHandler {
//
//
//    public void corsHandler(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors(
//                c -> {
//                    CorsConfigurationSource ccs = s -> {
//                        CorsConfiguration configuration = new CorsConfiguration();
//                        configuration.setAllowCredentials(true);
//                        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
//                        configuration.setAllowedHeaders(List.of("*"));
//                        configuration.setAllowedMethods(List.of("*"));
//                        return configuration;
//                    };
//                    c.configurationSource(ccs);
//                }
//
//        );
//    }
//
//}
