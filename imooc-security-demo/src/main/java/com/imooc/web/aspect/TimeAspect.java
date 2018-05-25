package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect//声明方面
@Component
public class TimeAspect {

	@Around("execution(* com.imooc.web.controller.UserController.*(..))")//指定切入点，和切入时间
	//这个意思是：UserController类里的所有方法都执行aop拦截。
	public Object handleControllerMethod(ProceedingJoinPoint pip) throws Throwable{
		System.out.println("time aspect start");
		Object[] args=pip.getArgs();//得到参数；
		for(Object arg:args){
			System.out.println("arg is"+ arg);
		}
		long start= new Date().getTime();
		Object object=pip.proceed();
		System.out.println("time aspect 耗时："+(new Date().getTime()-start));
		System.out.println("time aspect end");
		return object ;
		
	}
}








