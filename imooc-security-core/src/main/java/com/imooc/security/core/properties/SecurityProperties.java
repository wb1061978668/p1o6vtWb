package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="imooc.security")
public class SecurityProperties {

	private BrowserProperties browser=new BrowserProperties();
<<<<<<< HEAD
	private ValidateCodeProperties code=new ValidateCodeProperties();
=======

>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
<<<<<<< HEAD

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}
	
	
=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	
}
