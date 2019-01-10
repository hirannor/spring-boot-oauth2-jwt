package com.myapp.resourcesrv.service;



import com.myapp.resourcesrv.dto.User;

import java.util.List;

/**
 * UserManagementService API
 * 
 * @author mate.karolyi
 */
public interface UserManagementService
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
