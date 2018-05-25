package com.imooc.security.browser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 
* @ClassName: BrowerSecurityController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wb  
* @date 2018年4月8日  
*
 */
@RestController
public class BrowerSecurityController {
		private Logger logger=LoggerFactory.getLogger(getClass());
		private RequestCache requestCache=new HttpSessionRequestCache();//创建一个请求缓存
		private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();//spring 的工具
		@Autowired
		private SecurityProperties securityProperties;
/**
 * @throws IOException 
 * 
* @Title: requireAuthentication  
* @Description: TODO(当需要身份认证时跳转到这里)  
* @param @param request
* @param @param response
* @param @return    参数  
* @return String    返回类型  
* @throws
 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)//401未授权的状态码
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException{
		SavedRequest saveRequest=requestCache.getRequest(request, response);//得到请求的缓存；
		if(saveRequest!=null){
			String targetUrl =saveRequest.getRedirectUrl();
			logger.info("引发跳转的请求是："+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){//判断跳转的请求是否是以.html结尾
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());//第3个参数是要跳转的url
			}
		}
	 return new SimpleResponse("访问的服务需要认证，请引导用户到登录页");	
	}
}
