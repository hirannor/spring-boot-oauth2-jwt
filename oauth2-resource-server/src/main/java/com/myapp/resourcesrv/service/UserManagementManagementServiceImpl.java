package com.myapp.resourcesrv.service;

import com.myapp.resourcesrv.dto.User;
import com.myapp.resourcesrv.repository.UserManagementRepository;
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
		for(com.myapp.resourcesrv.entity.User user : userManagementRepository.findAll())
		{
			users.add(modelMapper.map(user, com.myapp.resourcesrv.dto.User.class));
		}
		return users;
	}

	@Override
	public com.myapp.resourcesrv.dto.User findUserById(Long id) {
		return	modelMapper.map(userManagementRepository.findById(id).get(), com.myapp.resourcesrv.dto.User.class);
	}

	@Override
	public com.myapp.resourcesrv.dto.User addUser(com.myapp.resourcesrv.dto.User user)
	{
		return	modelMapper.map(userManagementRepository.save(modelMapper.map(user, com.myapp.resourcesrv.entity.User.class)), com.myapp.resourcesrv.dto.User.class);
	}

	@Override
	public void removeUserById(Long id)
	{
		userManagementRepository.deleteById(id);
	}

}
