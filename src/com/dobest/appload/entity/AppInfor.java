package com.dobest.appload.entity;

public class AppInfor {
	
	private String showurl;
	private String deeplink;//应用启动锚点
	private String downurl;//下载地址
	private String appname;//应用的名字
	private String desc;//应用描述
	
	public String getShowurl() {
		return showurl;
	}
	public void setShowurl(String showurl) {
		this.showurl = showurl;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public String getDownurl() {
		return downurl;
	}
	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
//	public String toString()
//	{
//		return "showurl:"+showurl+"\ndeeplink:"+deeplink+"\ndownurl:"+downurl+"\nappname:"+appname+"\ndesc:"+desc;
//	}
}
