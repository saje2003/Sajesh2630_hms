package com.hmsapp.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithKey);
    }

    public String generateToken(String usernmae) {
        return JWT.create()
                .withClaim("name", usernmae)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);

       return decodedJWT.getClaim("name").asString();
    }
}
