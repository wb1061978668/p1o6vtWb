package com.imooc.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
//@Component  Springboot就不需要在web.xml中配置了直接使用@Component就可以了
//如果是第3方的过滤器，则需要新建一个类：如现在将TimerFilter当作第3方的过滤器；
public class TimerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("time filter start");
		long start=new Date().getTime();
		chain.doFilter(req, resp);//调用下一个过滤器链
		System.out.println("time filter耗时:"+(new Date().getTime()-start));
		System.out.println("time filter finish");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
		
	}

}
