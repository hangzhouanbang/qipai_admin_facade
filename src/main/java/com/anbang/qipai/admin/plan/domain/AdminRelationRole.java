package com.anbang.qipai.admin.plan.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin_ref_role")
public class AdminRelationRole {
	private String id;// 关系id
	private String adminId;// 管理员id
	private String roleId;// 角色id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "AdminRelationRole [id=" + id + ", adminId=" + adminId + ", roleId=" + roleId + "]";
	}

}
