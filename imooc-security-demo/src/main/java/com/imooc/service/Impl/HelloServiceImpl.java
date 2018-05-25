package com.imooc.service.Impl;

import org.springframework.stereotype.Service;

import com.imooc.service.HelloService;

/**
 * 
* @ClassName: HelloService  
* @Description: TODO(随便写一个service层。测试自定义注解可以调用service层)  
* @author wb  
* @date 2018年2月28日  
*
 */
@Service//service层
public class HelloServiceImpl implements HelloService{
	/* (non-Javadoc)
	 * @see com.imooc.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}
}
