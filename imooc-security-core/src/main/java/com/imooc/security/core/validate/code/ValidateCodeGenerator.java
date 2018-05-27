package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
* @ClassName: ValidateCodeGenerator  
* @Description: 增加验证码的逻辑可配置 ，注意是逻辑可配置
* @author wb  
* @date 2018年5月28日  
*
 */
public interface ValidateCodeGenerator {
	public ImageCode createImageCode(ServletWebRequest request) ;
}
