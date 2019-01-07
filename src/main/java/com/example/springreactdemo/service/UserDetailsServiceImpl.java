package com.example.springreactdemo.service;

import com.example.springreactdemo.domain.Credentials;
import com.example.springreactdemo.repository.CredentialsRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service("MyUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private CredentialsRepository credentialsRepository;

    public UserDetailsServiceImpl(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByUserName(username);
        if(credentials == null) {
            throw new UsernameNotFoundException(username);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(credentials.getRole());

        return new User(credentials.getUserName(), credentials.getPassword(), Arrays.asList(authority));

    }
}
