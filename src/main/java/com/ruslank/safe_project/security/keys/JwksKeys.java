package com.ruslank.safe_project.security.keys;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Component
public class JwksKeys {

    public RSAKey rsaKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();

            return new RSAKey.Builder(rsaPublicKey)
                    .privateKey(rsaPrivateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No keys");
        }

    }
}
