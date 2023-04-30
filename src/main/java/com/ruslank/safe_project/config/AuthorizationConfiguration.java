package com.ruslank.safe_project.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
//import com.ruslank.safe_project.cors.CustomCorsHandler;
//import com.ruslank.safe_project.cors.CustomCorsHandler;
import com.ruslank.safe_project.security.keys.JwksKeys;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@AllArgsConstructor
public class AuthorizationConfiguration {

    private final JwksKeys jwksKeys;

//    private final CustomCorsHandler customCorsHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        OAuth2AuthorizationServerConfiguration
                .applyDefaultSecurity(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        httpSecurity.exceptionHandling(
                e -> e.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        );
//        customCorsHandler.corsHandler(httpSecurity);
        return httpSecurity
                .build();
    }

        @Bean
        @Order(2)
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {



            return httpSecurity
                    .cors().and()
                    .formLogin()
                    .and()
                    .authorizeHttpRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
//                    .cors()
//                    .and()
//                    .csrf().disable()
                    .build();
        }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("*")
//                        .allowedHeaders("*")
//                        .exposedHeaders("Access-Control-Allow-Origin") // add this line
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:3000/authorized")); //URLs you want to allow
//        configuration.setAllowedMethods(Arrays.asList("GET","POST")); //methods you want to allow
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(jwksKeys.rsaKey());
        return new ImmutableJWKSet(jwkSet);

    }
}

//http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://localhost:3000/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256
//http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256