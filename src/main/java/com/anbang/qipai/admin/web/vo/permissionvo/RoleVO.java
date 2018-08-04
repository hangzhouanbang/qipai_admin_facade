package com.anbang.qipai.admin.web.vo.permissionvo;

import com.anbang.qipai.admin.plan.bean.permission.Role;

public class RoleVO {
	private Role role;
	private Boolean selected;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
