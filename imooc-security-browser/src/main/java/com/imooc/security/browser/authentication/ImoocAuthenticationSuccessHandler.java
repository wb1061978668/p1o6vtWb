package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
/**
 * 
* @ClassName: ImoocAuthenticationSuccessHandler  
* @Description: 自定义登录成功后的处理，只需要实现AuthenticationSuccessHandler接口就可以了
* @author wb  
* @date 2018年4月9日  
*
 */
@Component("imoocAuthenticationSuccessHandler")
//public class ImoocAuthenticationSuccessHandler  implements AuthenticationSuccessHandler{
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{//为了实现redirect和json的通用处理
	private Logger logger=LoggerFactory.getLogger(ImoocAuthenticationSuccessHandler.class);
	@Autowired
	private ObjectMapper objectMapper;//工具类：springmvc在启动时会
	@Autowired
	private SecurityProperties securityProperties;//注入SecurityProperties
/**
 * 这个方法登陆成功就会被调用
 * authentication  是授权信息//{"authorities":[{"authority":"admin"}],"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"A212BB537959EE9E000D13D6F289E939"},"authenticated":true,"principal":{"password":null,"username":"aa","authorities":[{"authority":"admin"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},"credentials":null,"name":"aa"}
 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			logger.info("登录成功");
			response.setContentType("application/json;chartset=utf-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else{
			super.onAuthenticationSuccess(request, response, authentication);//springsecurity默认的处理器，默认的就是跳转方式
		}
		
	}

}
