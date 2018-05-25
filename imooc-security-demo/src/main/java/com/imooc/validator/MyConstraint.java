package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * 
* @ClassName: MyConstraint  
* @Description: 注解：hibernate validate 自定义注解 ;编辑校验逻辑，然后使用注解验证实体类是否通过
* @author wb  
* @date 2018年2月28日  
*
 */
@Retention(RetentionPolicy.RUNTIME)//retention美 [rɪˈtɛnʃən]n.保留;运行时的注解
@Target({ElementType.METHOD,ElementType.FIELD})//@Target表示这个注解可以作用在方法上，和属性上
@Constraint(validatedBy=MyConstraintValidator.class)//具体的实现 :验证的类是MyConstraintValidator
public @interface MyConstraint {
	/**
	 * 下面这3个属性必须要写
	* @Title: message  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	String message();//校验不过的时候要发送的信息

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
