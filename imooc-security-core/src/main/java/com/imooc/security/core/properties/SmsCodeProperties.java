package com.imooc.security.core.properties;
/**
 * 
* @ClassName: SmsCodeProperties  
* @Description: 短信验证码：初始化值时系统的默认配置
* @author wb  
* @date 2018年5月25日  
*
 */
public class SmsCodeProperties {
	private int length =6;
	private int expireIn =60;
	private String url;//验证码拦截的接口可配
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
