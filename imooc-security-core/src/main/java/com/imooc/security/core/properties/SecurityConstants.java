package com.imooc.security.core.properties;
/**
 * 
* @ClassName: SecurityConstants  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wb  
* @date 2018年6月7日  
*constant 英 [ˈkɒnstənt] [数]常数，常量;不变的事物;永恒值
 */
public class SecurityConstants {

	/**
	 * 默认的处理验证码的url前缀 /code
	 */
	public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
	/**
	 * 当请求需要身份认证时，默认跳转的url /authentication/require
	 * 
	 * @see SecurityController
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";// ===>/imooc-signIn.html"
	/**
	 * 默认的用户名密码登录请求处理url /authentication/form"
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
	/**
	 * 默认的手机验证码登录请求处理url /authentication/mobile
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
	/**
	 * 默认登录页面 /imooc-signIn.html
	 * 
	 * @see SecurityController 
	 */
	public static final String DEFAULT_LOGIN_PAGE_URL = "/imooc-signIn.html";
	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称 imageCode
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称 smsCode
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称 mobile
	 */
	public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
	/**
	 * session失效默认的跳转地址 /session/invalid
	 */
	public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

}
