package com.anbang.qipai.admin.plan.domain.permission;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role_ref_privilege")
public class RoleRelationPrivilege {
	private String id;// 关系id
	private String roleId;// 角色id
	private String privilegeId;// 权限id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	@Override
	public String toString() {
		return "RoleRelationPrivilege [id=" + id + ", roleId=" + roleId + ", privilegeId=" + privilegeId + "]";
	}

}
