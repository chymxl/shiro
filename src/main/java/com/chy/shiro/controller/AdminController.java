package com.chy.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chy.shiro.entity.User;
import com.chy.shiro.result.Utils;
import com.chy.shiro.service.IUserService;

@RestController
@RequestMapping(value="admin")
public class AdminController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="user/{username}",method=RequestMethod.GET)
	public String getUser(@PathVariable String username){
		return Utils.convertResult(true, userService.getUser(username));
	}
	
	@RequestMapping(value="user/add",method=RequestMethod.POST)
	public String addUser(User user){
		userService.addUser(user);
		return "true";
	}

}
