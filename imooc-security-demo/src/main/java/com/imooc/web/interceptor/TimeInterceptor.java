package com.imooc.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
* @ClassName: TimeInterceptor  
* @Description: TODO(拦截器不仅会拦截所以的controller层，spring里自己写的controller也会被拦截，注意知道一下就行了。)  
* @author wb  
* @date 2018年3月2日  
*
 */
@Component//springboot拦截器不但要使用@Component，
//还要在@Configuration注解所在的类中继承WebMvcConfigurerAdapter，并重写addInterceptors方法；
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object object, Exception ex)
			throws Exception {
		System.out.println("afterHandle:不管控制器处理过程中是否抛出异常，都会进入afterCompletion里。");
		Long start=(Long)req.getAttribute("starTime");
		System.out.println("afterHandle time interceptor耗时："+(new Date().getTime()-start));
		System.out.println("ex is "+ ex);
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle：处理完成。如果经过preHandler后，在控制层处理抛出异常，那么就不会进入postHandle，而会直接进入afterCompletion");
		Long start=(Long)req.getAttribute("starTime");
		System.out.println("postHandle time interceptor耗时："+(new Date().getTime()-start));
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object object) throws Exception {
		System.out.println("preHandle:开始处理前");
		System.out.println(((HandlerMethod)object).getBean().getClass().getName());
		System.out.println(((HandlerMethod)object).getMethod().getName());
		
		req.setAttribute("starTime", new Date().getTime());
		return true;//true和false确定是否继续向后执行，调用controller里的方法，和postHandle以及afterCompletion方法；
	}

}
