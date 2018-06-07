package com.imooc.security.core.properties;
/**
 * 
* @ClassName: ImageCodeProperties  
* @Description: 图片验证码：初始化值时系统的默认配置
* @author wb  
* @date 2018年5月25日  
*
 */
public class ImageCodeProperties extends SmsCodeProperties{
	private int width = 67;
	private int height = 23;
	
	
	public ImageCodeProperties() {
		setLength(4);
	}
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
	
	
}
