package com.example.springreactdemo.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.springreactdemo.security.jwt.JwtConstants.EXPIRATION;
import static com.example.springreactdemo.security.jwt.JwtConstants.SECRET_KEY;

@Component
public class JwtTokenGenerator {

    public String generate(Authentication authentication)
    {
        List<String> authorities = new ArrayList<String>(0);

        for(GrantedAuthority grantedAuthority : authentication.getAuthorities())
        {
            authorities.add(grantedAuthority.getAuthority());
        }
                return Jwts.builder().setSubject(authentication.getName())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, (SECRET_KEY.getBytes())).compact();
    }
}
