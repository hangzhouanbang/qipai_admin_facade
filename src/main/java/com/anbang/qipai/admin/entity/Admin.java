package com.anbang.qipai.admin.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "qipai_admin_facade")
public class Admin {
	private String id;// 管理员id
	private String user;// 管理员姓名
	private String pass;// 管理员密码
	private String nickName;// 管理员昵稱
	private String idCard;// 管理员身份证
	private Integer sex;// 管理员性别：0-男1-女

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getnickName() {
		return nickName;
	}

	public void setnickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", user=" + user + ", pass=" + pass + ", nickName=" + nickName + ", idCard=" + idCard
				+ ", sex=" + sex + "]";
	}

}
