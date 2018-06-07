package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
* @ClassName: SmsCodeAuthenticationProvider  
* @Description: 真正的校验逻辑
* @author wb  
* @date 2018年6月7日  
*
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	/**
	 * 身份认证的逻辑
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken=(SmsCodeAuthenticationToken)authentication;
		UserDetails userDetails= userDetailsService.loadUserByUsername((String)authenticationToken.getPrincipal());
		if(userDetails== null){
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		SmsCodeAuthenticationToken authenticationResult=new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);//是否是验证码类型的token
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	

}
