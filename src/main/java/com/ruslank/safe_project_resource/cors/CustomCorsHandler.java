//package com.ruslank.safe_project_resource.cors;
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
//    public void corsHandler(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors(
//                c -> {
//                    CorsConfigurationSource ccs = source -> {
//                        CorsConfiguration corsConfiguration = new CorsConfiguration();
//                        corsConfiguration.setAllowCredentials(true);
//                        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
//                        corsConfiguration.setAllowedHeaders(List.of("*"));
//                        corsConfiguration.setAllowedMethods(List.of("*"));
//                        return corsConfiguration;
//                    };
//                    c.configurationSource(ccs);
//                }
//        );
//    }
//}
