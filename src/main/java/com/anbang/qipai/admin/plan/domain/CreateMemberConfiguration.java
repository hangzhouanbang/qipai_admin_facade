package com.anbang.qipai.admin.plan.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 创建新会员的设置
 * 
 * @author neo
 *
 */
public class CreateMemberConfiguration {

	/**
	 * 永远为1，只是为了数据库查询方便
	 */
	private String id;

	/**
	 * 新会员赠送金币数
	 */
	private Integer goldForNewMember;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGoldForNewMember() {
		return goldForNewMember;
	}

	public void setGoldForNewMember(Integer goldForNewMember) {
		this.goldForNewMember = goldForNewMember;
	}


}
