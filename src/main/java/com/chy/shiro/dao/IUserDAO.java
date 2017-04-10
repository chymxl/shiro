package com.chy.shiro.dao;

import java.util.List;

import com.chy.shiro.entity.User;

public interface IUserDAO {
	
	public User getUser(String username);
	
	public void addUser(User user);
	
	public List<User> queryUsers();

}
