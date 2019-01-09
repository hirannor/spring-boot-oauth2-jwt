package com.example.springreactdemo.database;

import com.example.springreactdemo.entitiy.Credentials;
import com.example.springreactdemo.entitiy.User;
import com.example.springreactdemo.repository.CredentialsRepository;
import com.example.springreactdemo.repository.UserManagementRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Database initializer component
 * It is Responsible for creating demo users for the app
 *
 * @author mate.karolyi
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseInitializer.class);

    private UserManagementRepository userManagementRepository;
    private CredentialsRepository credentialsRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DatabaseInitializer(UserManagementRepository userManagementRepository, CredentialsRepository credentialsRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userManagementRepository = userManagementRepository;
        this.credentialsRepository = credentialsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {
        LOGGER.info("Initializing database with demo users.");
        Credentials credentials1 = getCredentials("hirannor", bCryptPasswordEncoder.encode("password"), "ADMIN");
        Credentials credentials2 = getCredentials("duvy", bCryptPasswordEncoder.encode("password"), "USER");

        User user1 = getUserDetails("hirannor", "Máté", "Károlyi");
        User user2 = getUserDetails("duvy", "Dávid", "Klusóczki");

        credentialsRepository.saveAll(Arrays.asList(credentials1, credentials2));
        userManagementRepository.saveAll(Arrays.asList(user1, user2));
    }

    private Credentials getCredentials(String userName, String password, String role)
    {
        return new Credentials(userName, password, role);
    }

    private User getUserDetails(String userName, String firstName, String lastName)
    {
        return new User(userName, firstName, lastName, RandomUtils.nextInt(1,99), RandomStringUtils.randomAlphabetic(3) + "@opx.hu");
    }
}
