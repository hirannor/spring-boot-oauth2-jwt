package com.example.springreactdemo.security.filter;

import com.example.springreactdemo.security.jwt.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.springreactdemo.security.jwt.JwtConstants.TOKEN_PREFIX;


/**
 * Jwt authorization filter implementation
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
//        String user = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
//                .build()
//                .verify(header.replace(TOKEN_PREFIX, ""))
//                .getSubject();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConstants.SECRET_KEY.getBytes())
                    .parseClaimsJws(header.replace(TOKEN_PREFIX,"")).getBody();
            String username = claims.getSubject();

            List<String> authorities = (List<String>)claims.get("authorities");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            for(String authority : authorities)
            {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
            UsernamePasswordAuthenticationToken authentication = null;
            if(username != null) {
                authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception ex) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}


