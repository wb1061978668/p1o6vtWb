package com.imooc.security.core.validate.code;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import com.imooc.security.core.properties.SecurityProperties;


/**
 * 
* @ClassName: SmsCodeGeneratorImpl  
* @Description: 生成短信验证码
* @author wb  
* @date 2018年6月6日  
*
 */
@Component("smsCodeGeneratorImpl")
public class SmsCodeGeneratorImpl implements ValidateCodeGenerator {
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	public ValidateCode createCode(ServletWebRequest request) {
		String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
	}
	


	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
