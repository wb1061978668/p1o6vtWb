package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
/**
 * 
* @ClassName: ImoocAuthenticationFailHandler  
* @Description:自定义登录失败后的处理
* @author wb  
* @date 2018年4月9日  
*
 */
@Component("imoocAuthenticationFailHandler")
//public class ImoocAuthenticationFailHandler implements AuthenticationFailureHandler{
	public class ImoocAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler{
	private Logger logger=LoggerFactory.getLogger(ImoocAuthenticationFailHandler.class);
	@Autowired
	private ObjectMapper objectMapper;//工具类：springmvc在启动时会
	@Autowired
	private SecurityProperties securityProperties;//注入SecurityProperties
/**
 * AuthenticationException 是登录失败的错误原因；它是UsernameNotFoundException，AccountStatusException，BadCredentialsException等异常的父类
 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			logger.info("登录失败");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());//设置响应的状态码为500
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
//			response.getWriter().write(objectMapper.writeValueAsString(exception));
		}else{
			super.onAuthenticationFailure(request, response, exception);//springsecurity默认的，跳转方式,跳转到一个页面上去
			
		}
		
	}

}
