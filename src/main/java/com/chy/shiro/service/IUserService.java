package com.chy.shiro.service;

import java.util.List;

import com.chy.shiro.entity.User;

public interface IUserService {
	
	public User getUser(String username);
	
	public void insertUser(User user);
	
	public List<User> queryByPage(int pageNo, int pageSize);

}
