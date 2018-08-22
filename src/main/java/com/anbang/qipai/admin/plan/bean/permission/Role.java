package com.anbang.qipai.admin.plan.bean.permission;

import java.util.List;

public class Role {
	private String id;// 角色id
	private String role;// 角色名称
	private List<Privilege> privilegeList;// 权限列表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

}
