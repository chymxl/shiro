package com.chy.shiro.realm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;

import com.chy.shiro.entity.User;
import com.chy.shiro.service.IUserService;

public class MyJdbcRealm extends JdbcRealm{
	
	@Resource(name="redisTemplate")
    private HashOperations<String, String, User> hashOps;
	@Autowired
	private IUserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(MyJdbcRealm.class);
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken)token;
		String username = uptoken.getUsername();
		User user = hashOps.get("user", username);
		if(user == null)
			user = userService.getUser(username);
		if(user == null){
			throw new AccountException("Null username are not allowed by this realm");
		}
		hashOps.put("user", user.getUsername(), user);
		String password = user.getPassword();
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
		return info;
	}
	
}
