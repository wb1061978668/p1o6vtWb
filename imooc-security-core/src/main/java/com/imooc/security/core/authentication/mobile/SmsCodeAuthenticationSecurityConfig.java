/**  
* @Title: SmsCodeAuthenticationSecurityConfig.java  
* @Package com.imooc.security.core.authentication.mobile  
* @Description: TODO(用一句话描述该文件做什么)  
* @author wangb  
* @date 2018年6月7日  
* @version V1.0  
*/ 
package com.imooc.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: SmsCodeAuthenticationSecurityConfig  
 * @Description: TODO(短信验证码认证过滤器配置)  
 * @author wb  
 * @date 2018年6月7日  
 *    
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	@Autowired	
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	@Override
	public void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter=new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
	
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider=new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		http.authenticationProvider(smsCodeAuthenticationProvider)
		.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//将smsCodeAuthenticationFilter过滤器添加到UsernamePasswordAuthenticationFilter过滤器后面
	}

	
}
