package com.anbang.qipai.admin.web.vo;

import java.util.Date;

import com.anbang.qipai.admin.plan.domain.permission.Admin;

public class UserVo {
	private Admin admin;
	private Date loginTime;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
