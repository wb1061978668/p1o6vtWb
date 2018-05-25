package com.imooc.security.browser;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 1、处理用户信息获取逻辑；
 * 2、处理用户校验逻辑；
 * 3、处理密码加密解密；
* @ClassName: MyUserDetailsService  
* @Description:重写认证
* @author wb  
* @date 2018年3月7日  
*
 */
@Component//需要将它设置为spring的bean
public class MyUserDetailsService implements UserDetailsService {
//	@Autowired
	@Inject
	private PasswordEncoder passwordEncoder;
	//TODO 将来可以注入dao层连接数据库，查找用户名，方便下面方法进行认证；
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	/*
	 * UserDetails
	* <p>Title: loadUserByUsername</p>  
	* <p>Description: </p>  
	* @param username
	* @return
	* @throws UsernameNotFoundException  
	* @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		logger.info("登录用户名："+username);
		//根据用户名查找用户信息
		
		//这里这个User实现了UserDetails接口，new User(username, "123456", authorities);第1个参数是用户名；
//		第2个参数是从数据库读取出来的密码，第3个authorities参数是处理授权信息的集合,这里暂时给个admin权限。
//		return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		//根据查找到的用户信息判断是否被锁定
//		return new User(username, "123456", true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		
//		PasswordEncoder

		String password=passwordEncoder.encode("123456");
		logger.info("数据库的密码是："+password);
		return new User(username, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	
	}

}
