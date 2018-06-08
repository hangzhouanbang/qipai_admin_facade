package com.anbang.qipai.admin.plan.domain;

import java.util.Date;

public class Admin {
	private String id;// 管理员id
	private String nickname;// 管理员昵稱
	private String pass;// 管理员密码
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", nickname=" + nickname + ", pass=" + pass + ", createTime=" + createTime + "]";
	}

}
