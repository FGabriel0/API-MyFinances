package com.example.minhas_financias.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.minhas_financias.model.entity.Usuario;

@Service
public class tokenService {
	 @Value("${api.security.token.secret}")
	    private String secret;

	    public String generateToken(Usuario user){
	        try{
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            String token = JWT.create()
	                    .withIssuer("auth-api")
	                    .withSubject(user.getLogin())
	                    .withClaim("userId", user.getId())
	                    .withExpiresAt(genExpirationDate())
	                    .sign(algorithm);
	            return token;
	        } catch (JWTCreationException exception) {
	            throw new RuntimeException("Error while generating token", exception);
	        }
	    }

	    public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("auth-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception){
	            return "";
	        }
	    }
	    
	    public Long extractUserId(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("auth-api")
	                    .build()
	                    .verify(token)
	                    .getClaim("userId").asLong();
	        } catch (JWTVerificationException exception){
	            throw new RuntimeException("Invalid token", exception);
	        }
	    }

	    private Instant genExpirationDate(){
	        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	    }
	
}
