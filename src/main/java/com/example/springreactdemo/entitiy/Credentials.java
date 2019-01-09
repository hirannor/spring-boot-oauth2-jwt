package com.example.springreactdemo.entitiy;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Credentials DTO class
 *
 * @author mate.karolyi
 */
@Entity
@Table(name = "USER_CREDENTIALS")
public class Credentials {

    @Id
    private String userName;
    private String password;
    private String role;

    public Credentials() {
        super();
    }

    public Credentials(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


