package com.example.springreactdemo.service;


import com.example.springreactdemo.dto.User;

import java.util.List;

/**
 * User service API
 * 
 * @author hirannor
 *
 */
public interface UserService
{
	/**
	 * @return
	 */
	List<User> getUsers();

	/**
	 * @param id
	 * @return
	 */
	User findUserById(Long id);

	/**
	 * @param user
	 * @return
	 */
	User addUser(User user);

	/**
	 * @param id
	 */
	void removeUserById(Long id);
}
