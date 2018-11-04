package com.example.springreactdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springreactdemo.domain.User;

/**
 * Repository interface for CRUD operations
 * 
 * @author hirannor
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}