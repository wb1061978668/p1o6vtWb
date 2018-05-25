package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimerFilter;
import com.imooc.web.interceptor.TimeInterceptor;
/**
 * 
* @ClassName: WebConfig  
* @Description: web 组件配置 
* @author wb  
* @date 2018年3月1日  
*
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Autowired
	private TimeInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(interceptor);
	}

	@Bean
	public FilterRegistrationBean timeFilter(){
		FilterRegistrationBean registrationBean =new FilterRegistrationBean();
		TimerFilter timeFilter=new TimerFilter();
		registrationBean.setFilter(timeFilter);
		List<String> urls=new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);//可指定过滤器在那些url上起作用。这里/*表示所以url都过走过滤器
		return registrationBean;
	}
}
