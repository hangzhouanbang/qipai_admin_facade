package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.MemberDbo;

public interface MemberDao {

	List<MemberDbo> queryByConditionsAndPage(int page, int size, Sort sort, String nickname);

	long getAmount();

	void addMember(MemberDbo member);

	Boolean deleteMember(String[] ids);

	Boolean editMember(MemberDbo member);
}
