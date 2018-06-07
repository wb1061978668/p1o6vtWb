package com.imooc.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 
* @ClassName: ValidateCodeFilter  
* @Description: 验证码过滤器
* @author wb  
* @date 2018年5月24日
* OncePerRequestFilter是spring提供的一个工具类  ，它会保证我们的过滤器只会被调用一次
* InitializingBean:项目初始化时，将要验证码验证的拦截配置文件中的urls变为set集合中的url
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	private SessionStrategy sessionStrategy =new HttpSessionSessionStrategy();
//	private Set<String> urls=new HashSet<>();
	
	/**系统配置信息 */
	@Autowired
	private SecurityProperties securityProperties;
	
	/**系统中的校验码处理器*/
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	/**验证请求url与配置的url是否匹配的工具类*/
	private Map<String,ValidateCodeType> urlMap=new HashMap<String,ValidateCodeType>();
	
	/** 验证请求url与配置的url是否匹配的工具类 */
	private AntPathMatcher pathMatcher=new AntPathMatcher();//spring ant风格的工具类
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ValidateCodeType type=getValidateCodeType(request);
//		if(StringUtils.endsWith("/authentication/form", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
		if(type!=null){
			logger.info("校验请求("+request.getRequestURI()+")中的验证码,验证码类型"+type);
		
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request,response));
				logger.info("验证码校验通过");
			} catch (ValidateCodeException exception) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				exception.printStackTrace();
				return ;
			}
		}
	/*	
		boolean action=false;
		for(String url:urls){
			if(pathMatcher.match(url, request.getRequestURI())){//需要验证码拦截的urls与当前url匹配，如果匹配上了就返回true，就需要进行验证码验证
				action=true;
			}
		}
		if(action){
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		
		}*/
			filterChain.doFilter(request, response);
	}
/**
 * 
* @Title: getValidateCodeType  
* @Description: 获取校验码的类型，如果当前请求不需要校验，则返回null
* @param @param request
* @param @return    参数  
* @return ValidateCodeType    返回类型  
* @throws
 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result=null;
		if(!StringUtils.equalsIgnoreCase(request.getMethod(), "get")){
			Set<String> urls=urlMap.keySet();
			for(String url:urls){
				if(pathMatcher.match(url, request.getRequestURI())){
					result=urlMap.get(url);
				}
			}
			
		}
		return result;
	}

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		/*String[] configUrls=StringUtils.splitByWholeSeparator(securityProperties.getCode().getImage().getUrl(), ",");
		for(String configUrl:configUrls ){
			urls.add(configUrl);
		}
		urls.add("/authentication/form");*/
		urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);//用户名/密码登录时，要用图片验证码登录
		addUrlToMap(securityProperties.getCode().getImage().getUrl(),ValidateCodeType.IMAGE);
		urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);//验证码登录
		addUrlToMap(securityProperties.getCode().getSms().getUrl(),ValidateCodeType.SMS);
		
	}
	/**
	 * 
	* @Title: addUrlToMap  
	* @Description: 将系统中配置的需要校验验证码的URL根据校验的类型放入map
	* @param @param urlString
	* @param @param type    参数  
	* @return void    返回类型  
	* @throws
	 */
	protected void addUrlToMap(String urlString,ValidateCodeType type) {
		if(StringUtils.isNotBlank(urlString)){
			String[] urls=StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for(String url:urls){
				urlMap.put(url, type);
			}
		}

	}

	/*private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		// TODO Auto-generated method stub
		ImageCode codeInSession=(ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
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
*/
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

/*	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}*/

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
