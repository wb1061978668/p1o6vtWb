package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;

@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();//使用spring自带的session工具类
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private ValidateCodeGenerator validateCodeGeneratorImpl;
	@Autowired
	private SmsCodeGeneratorImpl smsCodeGeneratorImpl;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
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
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		ImageCode imageCode=(ImageCode)validateCodeGeneratorImpl.createImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);//将图片验证码放入session中
		ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());//将验证码变成图片的输出流，写到响应里
	}
	/**
	 * @throws ServletRequestBindingException 
	* @Title: createSmsCode  
	* @Description: 发送短信验证码接口
	* @param @param request
	* @param @param response
	* @param @throws IOException    参数  
	* @return void    返回类型  
	* @throws
	 */
	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletRequestBindingException{
			
		ValidateCode smsCode=smsCodeGeneratorImpl.createImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);//将图片验证码放入session中
		String mobile=ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
		
	}
}
