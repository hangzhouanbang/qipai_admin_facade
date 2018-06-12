package com.anbang.qipai.admin.web.vo;

import com.anbang.qipai.admin.plan.domain.permission.Role;

public class RoleVo {
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
