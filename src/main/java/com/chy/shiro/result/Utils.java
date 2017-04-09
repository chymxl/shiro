package com.chy.shiro.result;

import com.alibaba.fastjson.JSONObject;

public class Utils {
	
	public static String convertResult(boolean success, Object data, String message){
		JSONObject result = new JSONObject();
		result.put("success", success);
		if(success){
			result.put("data", data);
		}else{
			result.put("message", message);
		}
		return result.toJSONString();
	}
	
	public static String convertResult(boolean success, Object data){
		return convertResult(success, data, null);
	}
	
	public static String convertResult(boolean success, String message){
		return convertResult(success, null, message);
	}

}
