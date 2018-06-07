package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
* @ClassName: ValidateCodeProcessor  
* @Description: 校验码处理器，封装不同校验码的处理逻辑 
* @author wb  
* @date 2018年6月6日  
*
 */
public interface ValidateCodeProcessor {
/**
 * 验证码放入session时的前缀
 */
	String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";
	/**
	* @Title: create   
	* @Description: 创建校验码  ServletWebRequest虽然叫request,但是request和response都可以放到这里
	* @param @param request
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws
	 */
	void create(ServletWebRequest request) throws Exception;
	/**
	* @Title: volidate  
	* @Description: 校验验证码
	* @param @param servletWebRequest    参数  
	* @return void    返回类型  
	* @throws
	 */
	void validate(ServletWebRequest servletWebRequest);
}
