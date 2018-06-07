package com.imooc.security.core.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;
/**
 * [ˈprəʊsesə(r)]n.加工;数据处理机;
 * progress n. 进步;   前进; 英 [ˈprəʊgres]
 * Process vt.处理;加工; 英 [ˈprəʊses] 
* @ClassName: ImageCodeProcessor  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wb  
* @date 2018年6月6日  
*
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode)
			throws Exception {
		ImageIO.write(imageCode.getImage(), "jpeg", request.getResponse().getOutputStream());//将验证码变成图片的输出流，写到响应里
	}

}
