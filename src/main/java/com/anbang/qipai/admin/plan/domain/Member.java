package com.anbang.qipai.admin.plan.domain;

import java.util.Date;

public class Member {
	private String id;// 会员id
	private String nickname;// 会员昵称
	private String pass;// 会员密码
	private String sex;// 会员性别
	private Integer age;// 会员年龄
	private Date birthday;// 会员出生日期
	private String adress;// 会员地址

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", nickname=" + nickname + ", pass=" + pass + ", sex=" + sex + ", age=" + age
				+ ", birthday=" + birthday + ", adress=" + adress + "]";
	}

}
