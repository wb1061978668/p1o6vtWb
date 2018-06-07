package com.imooc.security.core.validate.code.sms;

public interface SmsCodeSender {
	public void send(String mobile,String code);
}
