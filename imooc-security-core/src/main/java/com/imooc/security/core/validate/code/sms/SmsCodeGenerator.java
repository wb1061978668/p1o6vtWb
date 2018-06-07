package com.imooc.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

/**
 * 
* @ClassName: SmsCodeGenerator  
* @Description: 生成短信验证码  
* @author wb  
* @date 2018年6月6日  
*
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	public ValidateCode createCode(ServletWebRequest request) {
		String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());

	}

}
