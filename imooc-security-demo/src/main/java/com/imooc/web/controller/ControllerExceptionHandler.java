package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.exception.UserNotExistException;

@ControllerAdvice//@ControllerAdvice 拦截异常并统一处理 
@ResponseBody
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//服务器内部错误
public class ControllerExceptionHandler {
@ExceptionHandler(UserNotExistException.class)
	public Map<String,Object> handleUserNotExistException(UserNotExistException ue){
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("id", ue.getId());
	map.put("message", ue.getMessage());
		return map;
	}
}
