package com.chy.shiro.service;

import com.chy.shiro.entity.User;

public interface IUserService {
	
	public User getUser(String username);
	
	public void insertUser(User user);

}
