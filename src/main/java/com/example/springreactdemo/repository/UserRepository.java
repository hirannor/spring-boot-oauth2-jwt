package com.example.springreactdemo.repository;

import com.example.springreactdemo.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CRUD operations
 * 
 * @author hirannor
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>
{

}