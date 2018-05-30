package com.anbang.qipai.admin.plan.domain;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "qipai_admin_user")
public class Admin {
	private String id;// 管理员id
	private String nickname;// 管理员昵稱
	private String pass;// 管理员密码
	private String user;// 管理员姓名
	private String idCard;// 管理员身份证
	private Integer sex;// 管理员性别：0-男1-女
	private String[] roles;// 管理员角色
	private Date createTime;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", nickname=" + nickname + ", pass=" + pass + ", user=" + user + ", idCard=" + idCard
				+ ", sex=" + sex + ", roles=" + Arrays.toString(roles) + ", createTime=" + createTime + "]";
	}
}
