package com.chy.shiro.dao;

import com.chy.shiro.entity.PasswordSalt;

public interface ISaltDAO {
	
	public PasswordSalt getSalt();
	
	public int updateSalt(PasswordSalt salt);

}
