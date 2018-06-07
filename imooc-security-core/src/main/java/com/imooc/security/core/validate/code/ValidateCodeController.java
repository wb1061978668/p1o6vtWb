package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class ValidateCodeController {
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	/*	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();//使用spring自带的session工具类
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private ValidateCodeGenerator validateCodeGeneratorImpl;
	@Autowired
	private SmsCodeGeneratorImpl smsCodeGeneratorImpl;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
*/	
	/**
	 * 
	* @Title: createCode  
	* @Description: 发送图片验证码接口
	* @param @param request
	* @param @param response
	* @param @throws IOException    参数  
	* @return void    返回类型  
	* @throws
	 */
/*	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		ImageCode imageCode=(ImageCode)validateCodeGeneratorImpl.createCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);//将图片验证码放入session中
		ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());//将验证码变成图片的输出流，写到响应里
	}
	
	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletRequestBindingException{
			
		ValidateCode smsCode=smsCodeGeneratorImpl.createCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);//将图片验证码放入session中
		String mobile=ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
		
	}*/
	 /**
	 * @throws Exception 
	 * 
	* @Title: createCode  
	* @Description: 发送图片验证码接口
	* @param @param request
	* @param @param response
	* @param @throws IOException    参数  
	* @return void    返回类型  
	* @throws
	* */
	@GetMapping("/code/{type}")
	public void createCode(HttpServletRequest request,HttpServletResponse response,@PathVariable String type) throws Exception{
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
		
	}
}
