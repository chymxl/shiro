package com.chy.shiro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chy.shiro.entity.User;
import com.chy.shiro.result.Utils;
import com.chy.shiro.service.IUserService;

@RestController
@RequestMapping(value="admin")
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Resource(name="redisTemplate")
    private HashOperations<String, String, User> hashOps;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@RequestMapping(value="user/{username}",method=RequestMethod.GET)
	public String getUser(@PathVariable String username){
		return Utils.convertResult(true, userService.getUser(username));
	}
	
	@RequestMapping(value="user/add",method=RequestMethod.POST)
	public String addUser(User user){
		taskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				String name = (String)Util.getAttribute("name");
				if(name == null){
					log.info("name is null");
					SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
					Util.addAttribute("name", Thread.currentThread().getName() + adf.format(new Date()));
				}
				userService.insertUser(user);
			}
		});
		return "true";
	}
	
	@RequestMapping(value="user/query", method=RequestMethod.GET)
	public String queryUsers(@RequestParam int pageNo, @RequestParam int pageSize){
		return Utils.convertResult(true, userService.queryByPage(pageNo, pageSize));
	}
	
	@RequestMapping(value="getUser")
	public User testRedis(){
		hashOps.put("user", "tom", userService.getUser("tom"));
		return hashOps.get("user", "tom");
	}

}
