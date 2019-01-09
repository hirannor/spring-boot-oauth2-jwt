package com.example.springreactdemo.service;

import com.example.springreactdemo.dto.User;
import com.example.springreactdemo.repository.UserManagementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserManagementService implementation of {@link UserManagementService}
 *
 * @author mate.karolyi
 */
@Service
public class UserManagementManagementServiceImpl implements UserManagementService
{
	private UserManagementRepository userManagementRepository;

	private ModelMapper modelMapper;

	public UserManagementManagementServiceImpl(UserManagementRepository userManagementRepository, ModelMapper modelMapper)
	{
		this.userManagementRepository = userManagementRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<User>(0);
		for(com.example.springreactdemo.entitiy.User user : userManagementRepository.findAll())
		{
			users.add(modelMapper.map(user, User.class));
		}
		return users;
	}

	@Override
	public com.example.springreactdemo.dto.User findUserById(Long id) {
		return	modelMapper.map(userManagementRepository.findById(id).get(), User.class);
	}

	@Override
	public User addUser(User user)
	{
		return	modelMapper.map(userManagementRepository.save(modelMapper.map(user, com.example.springreactdemo.entitiy.User.class)), User.class);
	}

	@Override
	public void removeUserById(Long id)
	{
		userManagementRepository.deleteById(id);
	}

}
