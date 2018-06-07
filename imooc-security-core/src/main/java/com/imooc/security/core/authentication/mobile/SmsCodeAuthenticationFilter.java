package com.imooc.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
/**
 * 
* @ClassName: SmsCodeAuthenticationFilter  
* @Description: 请参照UsernamePasswordAuthenticationFilter类编写
* @author wb  
* @date 2018年6月7日  
*
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
// ~ Static fields/initializers
// =====================================================================================

public static final String IMOOC_FORM_MOBILE_KEY = "mobile";
public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

private String mobileParameter = IMOOC_FORM_MOBILE_KEY;
private boolean postOnly = true;

// ~ Constructors
// ===================================================================================================

public SmsCodeAuthenticationFilter() {
super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
}

// ~ Methods
// ========================================================================================================

public Authentication attemptAuthentication(HttpServletRequest request,
	HttpServletResponse response) throws AuthenticationException {
if (postOnly && !request.getMethod().equals("POST")) {
	throw new AuthenticationServiceException(
			"Authentication method not supported: " + request.getMethod());
}

String mobile = obtainMobile(request);

if (mobile == null) {
	mobile = "";
}


mobile = mobile.trim();

SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);//实例化token

// Allow subclasses to set the "details" property
setDetails(request, authRequest);

return this.getAuthenticationManager().authenticate(authRequest);
}



/**
* 获取手机号
*/
protected String obtainMobile(HttpServletRequest request) {
return request.getParameter(mobileParameter);
}

/**
* Provided so that subclasses may configure what is put into the authentication
* request's details property.
*
* @param request that an authentication request is being created for
* @param authRequest the authentication request object that should have its details
* set
*/
protected void setDetails(HttpServletRequest request,
	SmsCodeAuthenticationToken authRequest) {
authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
}

/**
* Sets the parameter name which will be used to obtain the mobile from the login
* request.
*
* @param mobileParameter the parameter name. Defaults to "mobile".
*/
public void setMobileParameter(String mobileParameter) {
Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
this.mobileParameter = mobileParameter;
}



/**
* Defines whether only HTTP POST requests will be allowed by this filter. If set to
* true, and an authentication request is received which is not a POST request, an
* exception will be raised immediately and authentication will not be attempted. The
* <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
* authentication.
* <p>
* Defaults to <tt>true</tt> but may be overridden by subclasses.
*/
public void setPostOnly(boolean postOnly) {
this.postOnly = postOnly;
}

public final String getMobileParameter() {
return mobileParameter;
}


}