package com.lstu.stucontenst;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.lstu.stucontenst.entities.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class AuthenticationProvider {
    protected Algorithm algorithm = Algorithm.HMAC512("example-secret-key");
    protected JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("verification")
            .build();


    public Optional<String> createKey(
            String email,
            String password
    ) {
        try {
            return Optional.of(JWT.create()
                    .withClaim("email", email)
                    .withClaim("password", password)
                    .sign(algorithm));
        } catch (JWTCreationException exception) {
            return Optional.empty();
        }
    }


    public boolean isVerified(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }


    public Optional<UserEntity> getData(String key) {
        try {
            Map<String, Claim> claimMap = JWT.decode(key).getClaims();
            return Optional.of(new UserEntity(
                    claimMap.get("email").asString(),
                    claimMap.get("password").asString(),
                    List.of()));
        } catch (JWTDecodeException exception) {
            return Optional.empty();
        }
    }
}
