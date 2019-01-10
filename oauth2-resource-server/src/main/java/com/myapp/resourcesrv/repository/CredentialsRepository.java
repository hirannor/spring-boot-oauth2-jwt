package com.myapp.resourcesrv.repository;

import com.myapp.resourcesrv.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CredentialsRepository interface
 * Used for authentication process to retrieve credentials from a store.
 *
 * @author mate.karolyi
 */
@Repository
public interface CredentialsRepository  extends JpaRepository<Credentials, Long> {

    Credentials findByUserName(String userName);
}
