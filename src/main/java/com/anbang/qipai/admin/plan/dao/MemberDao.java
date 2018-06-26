package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.MemberDbo;

public interface MemberDao {

	List<MemberDbo> queryByConditionsAndPage(int page, int size, Sort sort, MemberDbo member);

	long getAmount(MemberDbo member);

	void addMember(MemberDbo member);

	Boolean deleteMember(String[] ids);

	void editMember(MemberDbo member);

	MemberDbo findMemberDbo(String id);

	long countNewMemberByTime(long startTime, long endTime);
}
