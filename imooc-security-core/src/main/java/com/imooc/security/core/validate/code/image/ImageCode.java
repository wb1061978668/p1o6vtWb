package com.imooc.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode {

	private BufferedImage image;
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public ImageCode(BufferedImage image, String code, LocalDateTime exireTime) {
		super(code,exireTime);
		this.image = image;
		
	}
	public ImageCode() {
		super();
	}
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code,expireIn);
		this.image=image;
		
	}
}
