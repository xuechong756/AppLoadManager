package com.dobest.appload.entity;

public class ShareBase {

	private String id;
	private String pakName;
	private String msg;
	private String os;
	private String chnl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPakName() {
		return pakName;
	}
	public void setPakName(String pakName) {
		this.pakName = pakName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getChnl() {
		return chnl;
	}
	public void setChnl(String chnl) {
		this.chnl = chnl;
	}
	
	public String toString()
	{
		return "id:"+id + "\npakName:"+pakName+"\nmsg:"+msg+"\nos:"+os+"\nchnl:"+chnl;
	}
}
