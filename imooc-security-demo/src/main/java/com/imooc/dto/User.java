package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;

public class User {
	/**
	 * 使用jsonview时：第1步：创建多个不同视图接口；
	 * @author wb
	 *
	 */
	public interface UserSimpleView{};//简单视图
	public interface UserDetailView extends UserSimpleView{};//详细视图
	
	private String id;
	@MyConstraint(message="这是wb自定义的一个hibernate validate注解")
	@ApiModelProperty(value = "用户名")
	private String username;
	@NotBlank(message="密码不能为空")
	@ApiModelProperty(value = "用户密码")
	private String password;
	@Past(message="生日只能是过去的时间")//验证必须是过去的时间
	private Date birthday;//前后端分离后，时间格式如何写；

	/**
	 * 使用jsonview时：第2步：在对应属性的get方法上设置哪些属性将要展示在哪个视图上
	 * @return
	 */
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonView(UserDetailView.class)//UserDetailView继承了UserSimpleView，所以UserDetailView视图也会有username属性
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
}
