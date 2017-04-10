package com.chy.shiro.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chy.shiro.dao.ISaltDAO;
import com.chy.shiro.dao.IUserDAO;
import com.chy.shiro.entity.PasswordSalt;
import com.chy.shiro.entity.User;
import com.chy.shiro.service.IUserService;

@Service(value="userService")
public class UserServiceImpl implements IUserService{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private ISaltDAO saltDAO;

	@Override
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	@Override
	public void insertUser(User user) {
		PasswordSalt salt = saltDAO.getSalt();
		int num = salt.getSalt();
		user.setPassword_salt(num + "");
		userDAO.addUser(user);
		salt.setSalt(++num);
		saltDAO.updateSalt(salt);
	}
	
	

}
