package com.ruslank.safe_project.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class WebConfiguration implements  WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println("WebConfig; " + request.getRequestURI());
////        response.setHeader("Access-Control-Allow-Origin", "*");
////        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
////        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Credentials", "true");
////        response.setHeader("Access-Control-Expose-Headers", "Authorization");
////        response.addHeader("Access-Control-Expose-Headers", "USERID");
////        response.addHeader("Access-Control-Expose-Headers", "ROLE");
////        response.addHeader("Access-Control-Expose-Headers", "responseType");
////        response.addHeader("Access-Control-Expose-Headers", "observe");
////        System.out.println("Request Method: " + request.getMethod());
////        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
////            try {
////                filterChain.doFilter(servletRequest, servletResponse);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        } else {
////            System.out.println("Pre-flight");
////            response.setHeader("Access-Control-Allow-Origin", "*");
////            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
////            response.setHeader("Access-Control-Max-Age", "3600");
////            response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers" + "Authorization, content-type," +
////                    "USERID" + "ROLE" +
////                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
////            response.setStatus(HttpServletResponse.SC_OK);
////        }
//    }
}
