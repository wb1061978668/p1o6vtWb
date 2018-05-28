package com.imooc.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
=======
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

<<<<<<< HEAD
import com.imooc.security.core.properties.SecurityProperties;

=======
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();//使用spring自带的session工具类
<<<<<<< HEAD
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private ValidateCodeGenerator validateCodeGeneratorImpl;
	
	@GetMapping("/code/image")
//	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		ImageCode imageCode=validateCodeGeneratorImpl.createImageCode(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);//将图片验证码放入session中
		ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());//将验证码变成图片的输出流，写到响应里
	}
	/*private ImageCode createImageCode(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
=======
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ImageCode imageCode=createImageCode(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);//将图片验证码放入session中
		ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());//将验证码变成图片的输出流，写到响应里
	}
	private ImageCode createImageCode(HttpServletRequest request) {
		int width = 67;
		int height = 23;
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		Graphics g=image.getGraphics();
		Random random=new Random();
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Time New Roman",Font.ITALIC,20));
		for(int i=0;i<155;i++){
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int xl=random.nextInt(12);
			int yl=random.nextInt(12);
			g.drawLine(x, y, xl, yl);
		}
		String sRand="";
<<<<<<< HEAD
		for(int i=0;i<ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getCode().getImage().getLength())
;i++){
=======
		for(int i=0;i<4;i++){
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
			String rand=String.valueOf(random.nextInt(10));
			sRand+=rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 13*i+6, 16);
		}
		g.dispose();
<<<<<<< HEAD
		return new ImageCode(image,sRand,ServletRequestUtils.getIntParameter(request.getRequest(), "expireIn", securityProperties.getCode().getImage().getExpireIn())
);
=======
		return new ImageCode(image,sRand,60);
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	}
	private Color getRandColor(int fc, int bc) {
		Random random= new Random();
		if(fc>255){
			fc=255;
		}
		if(bc>255){
			bc=255;
		}
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
<<<<<<< HEAD
	}*/
=======
	}
>>>>>>> 865d22b8fe49ee3bf297f17cb15523f75462a127
	
}
