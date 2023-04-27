package com.ruslank.safe_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

@Entity
@Data
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientId;
    private String secret;
    private String scope;
    private String redirectUri;
    private String authMethod;
    private String grantType;

    public static Client from(RegisteredClient registeredClient) {
        var client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setScope(registeredClient.getScopes()
                .stream()
                .findAny()
                .get());
        client.setRedirectUri(registeredClient.getRedirectUris()
                .stream()
                .findAny()
                .get());
        client.setGrantType(registeredClient.getAuthorizationGrantTypes()
                .stream()
                .findAny()
                .get()
                .getValue());
        client.setAuthMethod(registeredClient.getClientAuthenticationMethods()
                .stream()
                .findAny()
                .get()
                .getValue());
        return client;
    }

    public static RegisteredClient from(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scope(client.getScope())
                .redirectUri(client.getRedirectUri())
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(10)).build())
                .build();
    }
}
