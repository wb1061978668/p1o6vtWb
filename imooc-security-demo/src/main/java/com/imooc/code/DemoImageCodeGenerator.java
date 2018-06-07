package com.imooc.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.image.ImageCode;
/**
 * 
* @ClassName: DemoImageCodeGenerator  
* @Description:这里将bean的名字设定为 imageCodeGeneratorImpl，那么ValidateCodeBeanConfig类里生产验证码的逻辑就不会执行。重而实现了产生验证码的逻辑可配
* @author wb  
* @date 2018年5月28日  
*
 */
//@Component("imageCodeGeneratorImpl")//imageCodeGeneratorImpl
public class DemoImageCodeGenerator  implements ValidateCodeGenerator {

	@Override
	public ImageCode createCode(ServletWebRequest request) {
		System.out.println("这里可以写更高级的图形验证码生成代码");
		return null;
	}

}
