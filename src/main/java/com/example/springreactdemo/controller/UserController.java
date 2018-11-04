package com.example.springreactdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springreactdemo.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest API for user management
 * @author hirannnor
 *
 */
@Api(value = "user management")
@RequestMapping("/api/")
public interface UserController
{
	
	@ApiOperation(value = "List of available users", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
	})
	@GetMapping("users")
	List<User> getUsers();
	
	@ApiOperation(value = "Create a user", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User successfully created"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
	})
	@PostMapping("users")
	User create(@RequestBody User user);
	
	@ApiOperation(value = "Delete a user by id", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User is successfully deleted"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
	})
	@DeleteMapping("users/{id}")
	void delete(@PathVariable Long id);
	
	@ApiOperation(value = "Retrieve a user by Id", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User is successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
	})
	@GetMapping("users/{id}")
	User getUserById(@PathVariable Long id);
	
}
