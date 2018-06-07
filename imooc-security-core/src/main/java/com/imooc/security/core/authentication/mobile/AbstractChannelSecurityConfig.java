package com.imooc.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;//注入自定义的登录成功处理器；
	
	@Autowired
	protected AuthenticationFailureHandler imoocAuthenticationFailHandler;//注入自定义的登录失败处理器；
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception{
		http.formLogin()//指定了表单登录
//			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//1、指定个性化登录页面
			.loginPage("/imooc-signIn.html")//1、指定个性化登录页面
//			.loginPage("/authentication/require")//1、指定个性化登录页面
//			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//2、指定自定义个性化页面的url
			.loginProcessingUrl("/authentication/form")//2、指定自定义个性化页面的url
			.successHandler(imoocAuthenticationSuccessHandler)//指定登录成功之后使用自定义的处理器，而不使用默认的处理器
			.failureHandler(imoocAuthenticationFailHandler);//指定登录失败之后使用自定义的处理器，而不使用默认的处理器
		

	}
}
