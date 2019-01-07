package com.example.springreactdemo.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springreactdemo.security.JwtConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.springreactdemo.security.JwtConstants.SECRET_KEY;
import static com.example.springreactdemo.security.JwtConstants.TOKEN_PREFIX;


/**
 * Jwt authorization filter implementation
 *
 * This filter is responsible for authorization,
 * it reads the jwt from Authorization header and uses the jwt to validate the token
 *
 * @author mate.karolyi
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtConstants.HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String user = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                .build()
                .verify(header.replace(TOKEN_PREFIX, ""))
                .getSubject();

        UsernamePasswordAuthenticationToken authentication = null;
        if(user != null) {
            authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}


