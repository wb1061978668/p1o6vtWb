package com.imooc.security.core.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCodeProcessorHolder {
	/**
	 * 收集系统中所有的{@link ValidateCodeProcessor}接口的实现
	 * spring根据map依赖查找
	 */
	@Autowired//第1步：使用spring的依赖查找，首先得到ValidateCodeProcessor类下的所有子类的bean。
	private Map<String,ValidateCodeProcessor> validateCodeProcessors;
	
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}
	
	public ValidateCodeProcessor findValidateCodeProcessor(String type){
		String name=type.toLowerCase()+ValidateCodeProcessor.class.getSimpleName();//第2步：使用传过来的参数（image或sms）加上通过反射得到的类名ValidateCodeProcessor；
System.out.println(name);
		//		smsCodeProcessor imageCodeProcessor
		ValidateCodeProcessor processor=validateCodeProcessors.get(name);//第3步：根据得到的类目，在第1步的map中查找bean。
		if(processor== null){
			throw new ValidateCodeException("验证码处理器"+name+"不存在");
		}
		return processor;
	}
	
}
