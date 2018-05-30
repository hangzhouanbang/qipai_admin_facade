package com.anbang.qipai.admin.web.vo;

import com.anbang.qipai.admin.plan.domain.Privilege;

public class PrivilegeSelectedVo {
	private Privilege privilege;
	private Boolean selected = false;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((selected == null) ? 0 : selected.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrivilegeSelectedVo other = (PrivilegeSelectedVo) obj;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		return true;
	}

}
