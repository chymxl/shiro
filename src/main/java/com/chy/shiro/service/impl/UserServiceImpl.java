package com.chy.shiro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chy.shiro.controller.Util;
import com.chy.shiro.dao.ISaltDAO;
import com.chy.shiro.dao.IUserDAO;
import com.chy.shiro.entity.PasswordSalt;
import com.chy.shiro.entity.User;
import com.chy.shiro.service.IUserService;
import com.github.pagehelper.PageHelper;

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
		log.info((String)Util.getAttribute("name"));
		PasswordSalt salt = saltDAO.getSalt();
		int num = salt.getSalt();
		user.setPassword_salt(num + "");
		userDAO.addUser(user);
		salt.setSalt(++num);
		saltDAO.updateSalt(salt);
	}

	@Override
	public List<User> queryByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> users = userDAO.queryUsers();
		return users;
	}
	
	

}
