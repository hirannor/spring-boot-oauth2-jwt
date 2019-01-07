package com.example.springreactdemo.repository;

import com.example.springreactdemo.domain.Credentials;
import com.example.springreactdemo.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository  extends JpaRepository<Credentials, Long> {

    Credentials findByUserName(String userName);
}
