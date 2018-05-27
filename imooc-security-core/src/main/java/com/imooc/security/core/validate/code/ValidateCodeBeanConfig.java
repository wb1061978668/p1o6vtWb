package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
@Configuration
public class ValidateCodeBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	@Bean/*方法的名字，就是bean的名字*/
	@ConditionalOnMissingBean(name="imageCodeGeneratorImpl")//如果在spring容器中没有imageCodeGeneratorImpl这个bean就用这里的配置产生bean
	public ValidateCodeGenerator imageCodeGeneratorImpl(){
		ImageCodeGeneratorImpl codeGenerator=new ImageCodeGeneratorImpl();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
}
