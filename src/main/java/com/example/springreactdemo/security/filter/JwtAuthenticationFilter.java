package com.example.springreactdemo.security.filter;

import com.example.springreactdemo.domain.Credentials;
import com.example.springreactdemo.security.jwt.JwtTokenGenerator;
import com.example.springreactdemo.util.ObjectMapperUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.springreactdemo.security.jwt.JwtConstants.HEADER_STRING;
import static com.example.springreactdemo.security.jwt.JwtConstants.TOKEN_PREFIX;


/**
 * Jwt authentication filter implementation
 *
 * This filter is responsible for authentication process.
 * In case of successful authentication it creates a jwt token and sends back in the http header
 *
 * @author mate.karolyi
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;
    private JwtTokenGenerator jwtTokenGenerator;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator, AntPathRequestMatcher authenticationRequestMatcher) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.setRequiresAuthenticationRequestMatcher(authenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Credentials credentials = (Credentials) ObjectMapperUtil.deserialize(request, Credentials.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword(), new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        String token = JWT.create()
//                .withSubject(((User) authResult.getPrincipal()).getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
//                .sign(HMAC512(SECRET_KEY.getBytes()));
        String token = jwtTokenGenerator.generate(authentication);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        ObjectMapperUtil.getBaseReply("true", "You are successfully logged in", response);
    }
}
