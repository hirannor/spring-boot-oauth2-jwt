package com.example.springreactdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springreactdemo.domain.User;
import com.example.springreactdemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userDao)
	{
		this.userRepository = userDao;
	}

	@Override
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id)
	{
		return userRepository.findById(id).get();
	}

	@Override
	public User addUser(User user)
	{
		return userRepository.save(user);
	}

	@Override
	public void removeUserById(Long id)
	{
		userRepository.deleteById(id);
	}

}
