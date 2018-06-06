package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

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
