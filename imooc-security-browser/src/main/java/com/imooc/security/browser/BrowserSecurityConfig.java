package com.imooc.security.browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
/**
 * 
* @ClassName: BrowserSecurityConfig  
* @Description: web应用的security适配器 ,WebSecurityConfigurerAdapter是Security提供的适配器
* @author wb  
* @date 2018年3月6日  
*
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;//注入自定义的登录成功处理器；
	
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailHandler;//注入自定义的登录失败处理器；
	/**
	* @Title: passwordEncoder  
	* @Description: TODO(增加密码加密配置)   
	* @param @return    参数  
	* @return PasswordEncoder    返回类型  
	* @throws
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);//增加验证码接口可配置==的配置
		validateCodeFilter.afterPropertiesSet();//增加验证码接口可配置==的配置
		/*
		 * 用表单登录：所有的请求都需要表单身份认证
		 * */
		
//	  http.httpBasic()//指定了使用httpbasic方式登录
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//将validateCodeFilter过滤器加到UsernamePasswordAuthenticationFilter过滤器前面
		.formLogin()//指定了表单登录
		.loginPage("/imooc-signIn.html")//1、指定个性化登录页面
		
//		.loginPage("/authentication/require")//1、指定个性化登录页面
		.loginProcessingUrl("/authentication/form")//2、指定自定义个性化页面的url
		.successHandler(imoocAuthenticationSuccessHandler)//指定登录成功之后使用自定义的处理器，而不使用默认的处理器
		.failureHandler(imoocAuthenticationFailHandler)//指定登录失败之后使用自定义的处理器，而不使用默认的处理器
		.and()
		.authorizeRequests()//授权
		.antMatchers("/imooc-signIn.html").permitAll()//指定/imooc-signIn.html请求不需要认证，就可以访问
		.antMatchers("/a/getsuccess/require").permitAll()
		.antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/image").permitAll()//自定义的登录页也不需要认证，就可以访问
//		antMatchers()方法所使用的路径可能会包括Ant风格的通配符；.permitAll()允许请求没有任何的安全限制
		.anyRequest()//任何请求
		.authenticated()//认证请求
		.and()
		.csrf().disable();//3、个性化认证登录时，禁用csrf防护；
		
	}
}
