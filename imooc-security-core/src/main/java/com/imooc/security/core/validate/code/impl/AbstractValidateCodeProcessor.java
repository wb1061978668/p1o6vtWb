package com.imooc.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeException;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.ValidateCodeProcessor;
import com.imooc.security.core.validate.code.ValidateCodeType;
import com.imooc.security.core.validate.code.image.ImageCode;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
/**
 * 操作session的工具类
 */
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
	/**
	 * 收集系统中所有的{@link ValidateCodeGenerator}接口的实现
	 * spring根据map依赖查找
	 */
	@Autowired
	private Map<String,ValidateCodeGenerator> validateCodeGenerators;
	/**
	* @Title: create   
	* @Description: 创建校验码  ServletWebRequest虽然叫request,但是request和response都可以放到这里
	* @param @param request
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode=generate(request);
		save(request,validateCode);
		send(request,validateCode);
	}


	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
		
	}
	/**
	* @Title: getSessionKey  
	* @Description: 构建验证码放入session时的key
	* @param request
	* @return String    返回类型  
	 */
	private String getSessionKey(ServletWebRequest request) {
		
		return SESSION_KEY_PREFIX+getValidateCodeType(request).toString().toUpperCase();
	}

	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
	String type=StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
	
		return ValidateCodeType.valueOf(type.toUpperCase());
	}


	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type=getValidateCodeType(request).toString().toLowerCase();
		String generatorName=type+ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCoeGenerator=validateCodeGenerators.get(generatorName);//spring根据map依赖查找需要类型的bean
		if(validateCoeGenerator==null){
			throw new ValidateCodeException("验证码生成器"+generatorName+"不存在");
		}
		return (C) validateCoeGenerator.createCode(request);
	}
	/**
	 * 
	* @Title: send  
	* @Description: 发送校验码，由子类实现
	* @param @param request
	* @param @param validateCode
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;


	@Override
	public void validate(ServletWebRequest request) {
		// TODO Auto-generated method stub
		ValidateCodeType processorType=getValidateCodeType(request);
		String sessionKey=getSessionKey(request);
		C codeInSession =(C) sessionStrategy.getAttribute(request, sessionKey);
		String codeInRequest;
		
//				ImageCode codeInSession=(ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
				
				 try {
					codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), processorType.getParamNameOnValidate());
				} catch (ServletRequestBindingException e) {
					e.printStackTrace();
					throw new ValidateCodeException("获取验证码的值失败");
				}
				if(StringUtils.isBlank(codeInRequest)){
					throw new ValidateCodeException("验证码的值不能为空");
				}
				if(codeInSession==null){
					throw new ValidateCodeException("验证码不存在");
				}
				if(codeInSession.isExpried()){
					sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
					throw new ValidateCodeException("验证码已过期");
				}
				if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
					throw new ValidateCodeException("验证码不匹配");
				}
				sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
			}
	
	
}
