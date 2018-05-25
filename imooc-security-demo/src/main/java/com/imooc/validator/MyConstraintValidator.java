package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.HelloService;
/**
* @ClassName: MyConstraintValidator  
* @Description: TODO(hibernate validate 自定义注解的逻辑实现)  
* @author wb  
* @date 2018年2月28日  
*
 */
//这里不用写@comptent注解
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	/**
	 * 1、在自定义注解类的中可以调用service层。说明在hibernate validate自定义注解中可以调用数据库验证逻辑；
	 * 2、虽然这里调用了service层，但是在类上不用写@Compent注解；因为ConstraintValidator接口会自动将改类声明为一个javabean组件
	 */
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
		
	}
/**
 * value是执行校验时传入的值；
 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//这里比较校验逻辑
		helloService.greeting("Tom");
		System.out.println(value);
//		return false;
		return true;
	}

}
