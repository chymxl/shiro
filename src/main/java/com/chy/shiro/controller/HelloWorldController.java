package com.chy.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping(value="/hw")
	public String Welcome(){
		return "Hello World";
	}
	
	@RequestMapping(value="login")
	public String login(@RequestParam String username, @RequestParam String password){
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(upToken);
			log.info("login successfully");
		} catch (AuthenticationException e) {
			log.error("login failed", e);
		}
		
		return "Hello World";
		
	}
	

}
