package com.imooc.security.core.validate.code;

import java.io.IOException;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;
=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
<<<<<<< HEAD
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
=======
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

<<<<<<< HEAD
import com.imooc.security.core.properties.SecurityProperties;

=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
/**
 * 
* @ClassName: ValidateCodeFilter  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wb  
* @date 2018年5月24日
* OncePerRequestFilter是spring提供的一个工具类  ，它会保证我们的过滤器只会被调用一次
<<<<<<< HEAD
* InitializingBean:项目初始化时，将要验证码验证的拦截配置文件中的urls变为set集合中的url
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

	private AuthenticationFailureHandler authenticationFailureHandler;
	private SessionStrategy sessionStrategy =new HttpSessionSessionStrategy();
	private Set<String> urls=new HashSet<>();
	private SecurityProperties securityProperties;
	
	private AntPathMatcher pathMatcher=new AntPathMatcher();//spring ant风格的工具类
=======
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

	private AuthenticationFailureHandler authenticationFailureHandler;
	private SessionStrategy sessionStrategy =new HttpSessionSessionStrategy();
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
<<<<<<< HEAD
//		if(StringUtils.endsWith("/authentication/form", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
		boolean action=false;
		for(String url:urls){
			if(pathMatcher.match(url, request.getRequestURI())){//需要验证码拦截的urls与当前url匹配，如果匹配上了就返回true，就需要进行验证码验证
				action=true;
			}
		}
		if(action){
=======
		if(StringUtils.endsWith("/authentication/form", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		
		}
			filterChain.doFilter(request, response);
	}

<<<<<<< HEAD
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls=StringUtils.splitByWholeSeparator(securityProperties.getCode().getImage().getUrl(), ",");
		for(String configUrl:configUrls ){
			urls.add(configUrl);
		}
		urls.add("/authentication/form");
	}

=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		// TODO Auto-generated method stub
		ImageCode codeInSession=(ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		if(StringUtils.isBlank(codeInRequest)){
			throw new ValidateCodeException("验证码的值不能为空");
		}
		if(codeInSession==null){
			throw new ValidateCodeException("验证码不存在");
		}
		if(codeInSession.isExpried()){
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
			throw new ValidateCodeException("验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

<<<<<<< HEAD
	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
}
