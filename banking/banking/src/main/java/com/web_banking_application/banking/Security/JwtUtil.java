package com.web_banking_application.banking.Security;
//
//package com.web_banking_application.banking.security;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	private final String secretKey;

	@Autowired
    public JwtUtil(String secretKey) {
        this.secretKey = secretKey;
        System.out.println("Secret Key Injected: " + secretKey);
    }

    public String generateToken(String username, long userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

	public boolean validateToken(String jwt, String username) {
		// TODO Auto-generated method stub
		return false;
	}
	public Long getUserIdFromToken(String token) {
	    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	    return claims.get("userId", Long.class);
	}
}