package com.example.springreactdemo;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.springreactdemo.domain.User;
import com.example.springreactdemo.repository.UserRepository;

@SpringBootApplication
public class SpringReactDemoApplication implements CommandLineRunner
{
	private static final Logger LOGGER = LogManager.getLogger(SpringReactDemoApplication.class);

	private ApplicationContext applicationContext;
	private UserRepository userRepository;

	public SpringReactDemoApplication(UserRepository userRepository, ApplicationContext applicationContext)
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

		userRepository.saveAll(getDemoUsers());
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SpringReactDemoApplication.class, args);
	}

	private List<User> getDemoUsers()
	{
		User user1 = new User(1, "test1", "TestFirstName1", "TestLastName1", 26, "test1@test1.com");
		User user2 = new User(2, "test2", "TestFirstName2", "TestLastName2", 25, "test2@test2.com");
		User user3 = new User(3, "test3", "TestFirstName3", "TestLastName3", 24, "test3@test3.com");
		User user4 = new User(4, "test4", "TestFirstName4", "TestLastName4", 30, "test4@test4.com");
		User user5 = new User(5, "test5", "TestFirstName5", "TestLastName5", 45, "test5@test4.com");

		return Arrays.asList(user1, user2, user3, user4, user5);
	}

}
