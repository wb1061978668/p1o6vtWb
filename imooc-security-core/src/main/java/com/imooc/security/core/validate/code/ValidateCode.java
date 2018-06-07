package com.imooc.security.core.validate.code;

import java.time.LocalDateTime;
/**
 * 
* @ClassName: ValidateCode  
* @Description: 验证码：包括短信验证码
* @author wb  
* @date 2018年6月6日  
*
 */
public class ValidateCode {

	private String code;
	private LocalDateTime exireTime;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExireTime() {
		return exireTime;
	}
	public void setExireTime(LocalDateTime exireTime) {
		this.exireTime = exireTime;
	}
	public ValidateCode( String code, LocalDateTime exireTime) {
		super();

		this.code = code;
		this.exireTime = exireTime;
	}
	public ValidateCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidateCode( String code, int expireIn) {
		this.code=code;
		this.exireTime=LocalDateTime.now().plusSeconds(expireIn);
	}
	public boolean isExpried() {
		
		return LocalDateTime.now().isAfter(exireTime);
	}
}
