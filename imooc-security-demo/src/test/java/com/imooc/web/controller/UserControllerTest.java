package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	//mock英 [mɒk] adj.模拟的;仿制的;虚假的;不诚实的
	private MockMvc mockMvc;
	@Before
	public void setup(){
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();//虚拟一个mvc测试环境
	}
	@Test
	public void whenUploadSuccess() throws Exception{
		String result=mockMvc.perform(fileUpload("/file")
			.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
/*	@Test	
	public void whenQuerySuceescc() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user")
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())//expect英 [ɪkˈspekt] vt.期望;  isOK()是查看状态码
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}*/
		@Test	
	public void whenQuerySuceescc() throws Exception{
		mockMvc.perform(get("/a/user")
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())//expect英 [ɪkˈspekt] vt.期望;  isOK()是查看状态码
		.andExpect(jsonPath("$.length()").value(3));
	}
	@Test	
	public void whenQuerySuceescc2() throws Exception{
	/*	String result = mockMvc.perform(
				get("/a/user2").param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
						 .param("size", "15")
						 .param("page", "3")
						 .param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);*/
		String result =   mockMvc.perform(MockMvcRequestBuilders.get("/a/user2")//eclipse偏好设置
	    .param("username", "jojo")
		.param("age", "18")
		.param("ageTo","60")
		.param("xxx", "yyy2")
		/*.param("size", "15")
		.param("page", "3")
		.param("sort", "age,desc")*/
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())	
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//返回的结果长度为3
		.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoSuccess()  throws Exception {
		String result=	mockMvc.perform(get("/a/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/a/user/wangb")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());//预期结果是4xx的状态吗
	}
	
	
	@Test
	public void whenCreateSuccess() throws Exception {
		Date date=new Date();
		
//		String content="{\"username\":\"wangb\",\"password\":\"22\",\"birthday\":"+date.getTime()+"}";//对于时间前台直接传时间撮
		String content="{\"username\":\"wangb\",\"birthday\":"+date.getTime()+"}";//对于时间前台直接传时间撮
		String result=mockMvc.perform(MockMvcRequestBuilders.post("/a/user").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	/**
	 * 更新验证未来时间不通过
	 * @throws Exception
	 */
	@Test
	public void whenUpdateSuccess() throws Exception {
		Date date=  new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		String content="{\"id\":\"1\",\"username\":\"wangb\",\"password\":\"\",\"birthday\":"+date.getTime()+"}";//对于时间前台直接传时间撮
		String result=mockMvc.perform(MockMvcRequestBuilders.put("/a/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/a/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	
}
