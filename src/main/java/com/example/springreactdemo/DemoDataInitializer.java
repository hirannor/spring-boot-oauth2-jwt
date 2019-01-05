package com.example.springreactdemo;

import com.example.springreactdemo.domain.UserModel;
import com.example.springreactdemo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 *
 * Demo data initializer component
 *
 * It is Responsible for creating demo users for the app
 */
@Component
public class DemoDataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(DemoDataInitializer.class);

    private ApplicationContext applicationContext;
    private UserRepository userRepository;

    public DemoDataInitializer(UserRepository userRepository, ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans)
        {
            LOGGER.info("Registered Bean: {}", bean);
        }
        createDemoUsers();
    }

    private void createDemoUsers() {
        UserModel user1 = createUser("hirannor", "Máté", "Károlyi");
        UserModel user2 = createUser("john", "John", "Doe");
        UserModel user3 = createUser("arnold", "Arnold", "Schwarzenegger");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }

    private UserModel createUser(String userName, String firstName, String lastName)
    {
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setUserName(userName);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setAge(RandomUtils.nextInt(1,99));
        userModel.setEmailAddress(RandomStringUtils.randomAlphabetic(3) + "@" + "opx.hu");
        return userModel;
    }
}
