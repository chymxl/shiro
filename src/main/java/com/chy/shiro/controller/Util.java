package com.chy.shiro.controller;

import java.util.HashMap;
import java.util.Map;

public class Util {
	
	private static final ThreadLocal<Map<String, Object>> locals = new ThreadLocal<>();
	
	public static void setMap(Map<String, Object> map){
		locals.set(map);
	}
	
	public static void addAttribute(String key, Object val){
		locals.get().put(key, val);
	}
	
	public static Object getAttribute(String key){
		if(locals.get() == null){
			locals.set(new HashMap<String, Object>());
		}
		return locals.get().get(key);
	}
	
	public void removeMap(){
		locals.remove();
	}

}
