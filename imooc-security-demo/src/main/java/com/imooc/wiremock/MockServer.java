package com.imooc.wiremock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServer {
	public static void main(String[] args) throws IOException {
		WireMock.configureFor(8062);
		WireMock.removeAllMappings();
		//伪造一个测试桩
//		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("order/1")).willReturn(WireMock.aResponse().withBody("{\"id\":1}").withStatus(200)));
//	}
		

		mock("/order/1", "01");
		mock("/order/2", "02");
	}

	private static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
	}
}
