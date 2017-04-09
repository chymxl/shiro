package com.chy.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chy.shiro.dao.IUserDAO;
import com.chy.shiro.entity.User;
import com.chy.shiro.service.IUserService;

@Service(value="userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDAO userDAO;

	@Override
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	

}
