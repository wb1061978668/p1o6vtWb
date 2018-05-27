package com.imooc.security.core.properties;
/**
 * 
* @ClassName: ImageCodeProperties  
* @Description: 图片验证码：初始化值时系统的默认配置
* @author wb  
* @date 2018年5月25日  
*
 */
public class ImageCodeProperties {
	private int width = 67;
	private int height = 23;
	
	private int length =4;
	private int expireIn =60;
	private String url;//验证码拦截的接口可配
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
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
