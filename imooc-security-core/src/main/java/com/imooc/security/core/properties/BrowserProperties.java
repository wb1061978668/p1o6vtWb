package com.imooc.security.core.properties;

public class BrowserProperties {

	private String  loginPage= "/imooc-signIn.html";//设置默认的登录页面

	private LoginType loginType=LoginType.JSON;//设置默认返回json方式
	
	private int rememberMeSeconds=3600;//记住我的过期时间
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	
}
