package com.biz.app;

public class AddrVO {
	
	private String name;
	private String tel;
	private String post;
	private String city;
	private String addr;
	
	public AddrVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddrVO(String name, String tel, String post, String city, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.post = post;
		this.city = city;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "AddrVO [name=" + name + ", tel=" + tel + ", post=" + post + ", city=" + city + ", addr=" + addr + "]";
	}
	
}
