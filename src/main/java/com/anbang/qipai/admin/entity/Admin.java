package com.anbang.qipai.admin.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "qipai_admin_facade")
public class Admin {

	private String id;// 管理员id
	private String name;// 管理员名称
	private String pass;// 管理员密码
	private String idCard;// 管理员身份证
	private Integer sex;// 管理员性别：0-男1-女
	private Date createTime;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public Date getcreateTime() {
		return createTime;
	}

	public void setcreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pass=" + pass + ", idCard=" + idCard + ", sex=" + sex
				+ ", createTime=" + createTime + "]";
	}

}
