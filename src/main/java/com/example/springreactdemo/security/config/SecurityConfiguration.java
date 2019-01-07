package com.example.springreactdemo.security.config;

import com.example.springreactdemo.security.filter.JwtAuthenticationFilter;
import com.example.springreactdemo.security.filter.JwtAuthorizationFilter;
import com.example.springreactdemo.security.jwt.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security configuration
 * @author mate.karolyi
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationEntryPoint unauthorizedAuthenticationEntrypoint;
    private UserDetailsService userDetailsService;

    public SecurityConfiguration(@Qualifier("MyUserDetailsService") UserDetailsService userDetailsService, AuthenticationEntryPoint unauthorizedAuthenticationEntrypoint)
    {
        this.userDetailsService = userDetailsService;
        this.unauthorizedAuthenticationEntrypoint = unauthorizedAuthenticationEntrypoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .headers().frameOptions().sameOrigin()
            .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedAuthenticationEntrypoint)
            .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManagerBean(), getJwtTokenGenerator(), new AntPathRequestMatcher("/auth", "POST")))
                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()))
                .authorizeRequests().antMatchers("/auth", "/h2/**").permitAll()
                .antMatchers("/usermanagement/users").authenticated()
                .antMatchers("/usermanagement/users").hasRole("ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenGenerator getJwtTokenGenerator()
    {
        return new JwtTokenGenerator();
    }
}
