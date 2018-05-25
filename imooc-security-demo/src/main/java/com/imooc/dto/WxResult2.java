package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;


	public class WxResult2 {
		@ApiModelProperty(value = "图片路径")
		private String logo;
		@ApiModelProperty(value = "内容标题")
		private String title;
		@ApiModelProperty(value = "内容描述")
		private String desc;
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		public WxResult2() {
			super();
			// TODO Auto-generated constructor stub
		}
		public WxResult2(String logo, String title, String desc) {
			super();
			this.logo = logo;
			this.title = title;
			this.desc = desc;
		}
		@Override
		public String toString() {
			return "WxResult2 [logo=" + logo + ", title=" + title + ", desc=" + desc
					+ "]";
		}
		
}
