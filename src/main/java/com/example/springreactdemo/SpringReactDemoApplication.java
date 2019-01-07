package com.example.springreactdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point of spring boot app
 * @author mate.karolyi
 */
@SpringBootApplication
public class SpringReactDemoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringReactDemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
