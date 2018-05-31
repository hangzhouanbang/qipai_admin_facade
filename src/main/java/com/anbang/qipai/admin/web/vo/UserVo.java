package com.anbang.qipai.admin.web.vo;

import java.util.Date;
import java.util.Map;

public class UserVo {
	private String nickname;
	private Date loginTime;
	private Map<String, Boolean> privilegeList;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Map<String, Boolean> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(Map<String, Boolean> privilegeList) {
		this.privilegeList = privilegeList;
	}

	@Override
	public String toString() {
		return "UserVo [nickname=" + nickname + ", loginTime=" + loginTime + ", privilegeList=" + privilegeList + "]";
	}

}
