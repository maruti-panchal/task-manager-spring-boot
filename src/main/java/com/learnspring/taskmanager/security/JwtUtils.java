//package com.learnspring.taskmanager.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//@Component
//public class JwtUtils {
//
//
//    private static final long EXPIRATION = 1000 * 60 * 60;
//
//
//    private static final String SECRET = "secret908u89y89h2ujijij0f0o0[o0o0@(U90u903u0-0=249i0q20u9u8iu9u9u@(#*#nknanjanna";
//
//
//    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public static String createJwtToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(KEY, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Claims parseJwtToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
//    }
//
//    public String getUsernameFromJwtToken(String token) {
//        return parseJwtToken(token).getSubject();
//    }
//
//    public boolean validateJwtToken(String token, UserDetails userDetails,String username) {
//        return username.equals(userDetails.getUsername())&& expiredJwtToken(token);
//    }
//
//    public boolean expiredJwtToken(String token) {
//        return parseJwtToken(token).getExpiration().before(new Date());
//    }
//}



package com.learnspring.taskmanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour
    private static final String SECRET = "secret908u89y89h2ujijij0f0o0[o0o0@(U90u903u0-0=249i0q20u9u8iu9u9u@(#*#nknanjanna";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String createJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromJwtToken(String token) {
        return parseJwtToken(token).getSubject();
    }

    // validate: token must belong to userDetails and must NOT be expired
    public boolean validateJwtToken(String token, UserDetails userDetails, String username) {
        return username != null
                && username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // returns true if token is expired
    public boolean isTokenExpired(String token) {
        Date expiration = parseJwtToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
