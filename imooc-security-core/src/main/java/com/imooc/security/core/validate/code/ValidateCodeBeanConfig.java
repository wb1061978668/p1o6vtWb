package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
@Configuration
public class ValidateCodeBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	@Bean/*方法的名字，就是bean的名字*/
//	@ConditionalOnMissingBean(name="imageCodeGeneratorImpl")//如果在spring容器中没有imageCodeGeneratorImpl这个bean就用这里的配置产生bean
	@ConditionalOnMissingBean(name="imageValidateCodeGenerator")//如果在spring容器中没有imageValidateCodeGenerator这个bean就用这里的配置产生bean
	public ValidateCodeGenerator imageValidateCodeGenerator(){
		ImageCodeGenerator codeGenerator=new ImageCodeGenerator();//这里使用这个@ConditionalOnMissingBean注解，就增加了代码的灵魂性，可配读取配置文件的数据
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean/*方法的名字，就是bean的名字*/
	@ConditionalOnMissingBean(name="smsCodeSender")//如果在这里找到了bean，就不会用底下的方法了
//	@ConditionalOnMissingBean(SmsCodeSender.class)//另一种写法
	public SmsCodeSender smsCodeSender(){
		return new DefaultSmsCodeSender();
	}
}
