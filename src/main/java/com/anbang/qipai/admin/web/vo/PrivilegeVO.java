package com.anbang.qipai.admin.web.vo;

import com.anbang.qipai.admin.plan.domain.permission.Privilege;

public class PrivilegeVO {
	private Privilege privilege;
	private Boolean selected;

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
