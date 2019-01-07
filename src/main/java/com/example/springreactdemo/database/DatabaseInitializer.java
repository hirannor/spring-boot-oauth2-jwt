package com.example.springreactdemo.database;

import com.example.springreactdemo.domain.Credentials;
import com.example.springreactdemo.domain.UserModel;
import com.example.springreactdemo.repository.CredentialsRepository;
import com.example.springreactdemo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * Database initializer component
 * It is Responsible for creating demo users for the app
 *
 * @author mate.karolyi
 *
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseInitializer.class);

    private UserRepository userRepository;
    private CredentialsRepository credentialsRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DatabaseInitializer(UserRepository userRepository, CredentialsRepository credentialsRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {
        createDemoUsers();
    }

    private void createDemoUsers() {
        Credentials credentials1 = getCredentials("hirannor", bCryptPasswordEncoder.encode("password"), "ADMIN");
        UserModel user1 = getUserDetails("hirannor", "Máté", "Károlyi");

        credentialsRepository.save(credentials1);
        userRepository.save(user1);
    }

    private Credentials getCredentials(String userName, String password, String role)
    {
        return new Credentials(userName, password, role);
    }

    private UserModel getUserDetails(String userName, String firstName, String lastName)
    {
        return new UserModel(userName, firstName, lastName, RandomUtils.nextInt(1,99), RandomStringUtils.randomAlphabetic(3) + "@opx.hu");
    }
}
