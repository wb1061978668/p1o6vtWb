package com.imooc.security.browser.support;
/**
 * 
* @ClassName: SimpleResponse  
* @Description: TODO(返回状态码对象，和信息)  
* @author wb  
* @date 2018年4月8日  
*
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content=content;
	}
	private Object content;

	public Object getObject() {
		return content;
	}

	public void setObject(Object content) {
		this.content = content;
	}
	
}
