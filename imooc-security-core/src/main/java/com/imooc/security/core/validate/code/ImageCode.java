package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

	private BufferedImage image;
	private String code;
	private LocalDateTime exireTime;
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExireTime() {
		return exireTime;
	}
	public void setExireTime(LocalDateTime exireTime) {
		this.exireTime = exireTime;
	}
	public ImageCode(BufferedImage image, String code, LocalDateTime exireTime) {
		super();
		this.image = image;
		this.code = code;
		this.exireTime = exireTime;
	}
	public ImageCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImageCode(BufferedImage image, String code, int expireIn) {
		this.image=image;
		this.code=code;
		this.exireTime=LocalDateTime.now().plusSeconds(expireIn);
	}
	public boolean isExpried() {
		
		return LocalDateTime.now().isAfter(exireTime);
	}
	
}
