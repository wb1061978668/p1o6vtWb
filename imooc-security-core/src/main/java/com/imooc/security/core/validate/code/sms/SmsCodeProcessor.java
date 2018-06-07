package com.imooc.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * [ˈprəʊsesə(r)]n.加工;数据处理机;
 * progress n. 进步;   前进; 英 [ˈprəʊgres]
 * Process vt.处理;加工; 英 [ˈprəʊses] 
* @ClassName: SmsCodeProcessor  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wb  
* @date 2018年6月6日  
*
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{
	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode)
			throws Exception {
		String paramName=SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile=ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile,validateCode.getCode());
	}

	
}
