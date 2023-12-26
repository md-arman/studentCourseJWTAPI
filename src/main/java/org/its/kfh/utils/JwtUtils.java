package org.its.kfh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//This class i have take reference from google
//comments below i have added as per my understanding
//methods below for JWT token creation, validation, expiry, etc.
//using JJWT library

@Service
public class JwtUtils {

    private String SECRET_KEY = "secretKFH";

    //this method takes token and returns username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //this one takes token and returns expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //whether token expired or not
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //this method will generate token for the authenticated user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //creates a JWT token for this userDetails.getUsername
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        //Arman
        //Jwts library builder pattern used here
        //subject: person who is successfully authenticated
        //issuesAt : Date of issue: current date
        //expiration time set: 10 hours here
        //algo: signing the token using algo: HS256 with the secret key SECRET_KEY initialised above
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //vaidates the token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
