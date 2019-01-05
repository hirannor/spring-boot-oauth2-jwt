package com.example.springreactdemo.service;

import com.example.springreactdemo.domain.UserModel;
import com.example.springreactdemo.dto.User;
import com.example.springreactdemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User service implementation of {@link UserService}
 *
 * @author hirannor
 *
 */
@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;

	private ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userDao, ModelMapper modelMapper)
	{
		this.userRepository = userDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<User>();
		for(UserModel userModel : userRepository.findAll())
		{
			users.add(modelMapper.map(userModel, User.class));

		}
		return users;
	}

	@Override
	public User findUserById(Long id) {
		return	modelMapper.map(userRepository.findById(id).get(), User.class);
	}

	@Override
	public User addUser(User user)
	{
		return	modelMapper.map(userRepository.save(modelMapper.map(user, UserModel.class)), User.class);
	}

	@Override
	public void removeUserById(Long id)
	{
		userRepository.deleteById(id);
	}

}
