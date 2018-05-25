package com.imooc.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.dto.WxResult2;

@RestController
@RequestMapping(value="/a")
public class UserController {
	/*@PostMapping(value="user")
	public User create(@Valid @RequestBody User user,BindingResult errors){
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));//lambda表达式
		}
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}*/
	/**
	* @Title: create  
	* @Description: TODO(用于测试异常处理)  
	* @param @param user
	* @param @return    参数  
	* @return User    返回类型  
	* @throws
	 */
	@PostMapping(value="/user")
	@ApiOperation(value = "创建用户")
	public User create(@Valid @RequestBody User user){
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	/**
	 * 
	 * 修改
	 * @param user
	 * @param errors
	 * @return
	 */
	@PutMapping(value="user/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors){
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError=(FieldError)error;
				String message=fieldError.getField()+error.getDefaultMessage();
				
				System.out.println(message);//lambda表达式
		});
		}
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	@DeleteMapping(value="user/{id:\\d+}")
	public void delete(@PathVariable String id){
		System.out.println(id);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	@ApiOperation(value = "用户查询服务")
	public List<User> quesry(@RequestParam(name="username",required=false,defaultValue="Tom") String username){
		System.out.println(username);
		List<User> users=new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	@RequestMapping(value="/user2",method=RequestMethod.GET)
	@JsonView(User.UserSimpleView.class)//使用jsonview时：第3步：在controller方法上指定返回的结果使用哪个视图
	public List<User> quesry2(UserQueryCondition condition,@PageableDefault(page=2,size=15,sort=("age,asc")) Pageable pageable){//实体类
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize()+";"+pageable.getPageNumber()+";"+pageable.getSort());
		List<User> users=new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	/**
	 * ":\\d+"表示id只能接收数字，的正则表达式
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.GET)
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id){//实体类
		throw new RuntimeException("user not exist");//异常处理
//		throw new UserNotExistException(id);//异常处理
//		System.out.println("进入getInfo服务。");
//		User user=new User();
//		user.setUsername("tom");
//		user.setPassword("123");
//		return user;
	}
	@RequestMapping(value="/getsuccess/require",method=RequestMethod.GET)
//	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)//401未授权的状态码
	@ResponseBody
	public List<WxResult2> getsuccess(HttpServletRequest request,HttpServletResponse response) throws IOException{
	List<WxResult2> t=new ArrayList<WxResult2>();
	      t.add(new WxResult2("/img/pro_01.jpg","贷款标题111","贷款内容1贷款内容贷款内容贷款内容贷款内容贷款内容"));
	      t.add(new WxResult2("/img/pro_02.jpg","贷款标题2222","贷款内容2贷款内容贷款内容贷款内容贷款内容贷款内容"));
	      t.add(new WxResult2("/img/pro_03.jpg","贷款标题3333","贷款内容3贷款内容贷款内容贷款内容贷款内容贷款内容"));
	      
	 return t;	
	}
}
