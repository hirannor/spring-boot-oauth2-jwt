package com.example.springreactdemo.repository;

import com.example.springreactdemo.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserManagementRepository interface for usermanagement related CRUD operations
 * 
 * @author mate.karolyi
 *
 */
@Repository
public interface UserManagementRepository extends JpaRepository<User, Long>
{

}