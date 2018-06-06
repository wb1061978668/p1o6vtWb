package com.imooc.security.core.validate.code.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向"+mobile+"手机号发送的验证码是："+code);

	}

}
