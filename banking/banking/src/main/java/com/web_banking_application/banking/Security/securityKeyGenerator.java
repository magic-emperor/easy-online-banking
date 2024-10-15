package com.web_banking_application.banking.Security;


import java.security.Key;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
public class securityKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Secret Key: " + key.getEncoded());
    }
}