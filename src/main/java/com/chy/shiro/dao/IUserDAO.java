package com.chy.shiro.dao;

import com.chy.shiro.entity.User;

public interface IUserDAO {
	
	public User getUser(String username);
	
	public void addUser(User user);

}
