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

	public UserManagementManagementServiceImpl(UserManagementRepository userDao, ModelMapper modelMapper)
	{
		this.userManagementRepository = userDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<com.example.springreactdemo.dto.User>();
		for(com.example.springreactdemo.domain.User user : userManagementRepository.findAll())
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
	public com.example.springreactdemo.dto.User addUser(User user)
	{
		return	modelMapper.map(userManagementRepository.save(modelMapper.map(user, com.example.springreactdemo.domain.User.class)), User.class);
	}

	@Override
	public void removeUserById(Long id)
	{
		userManagementRepository.deleteById(id);
	}

}
