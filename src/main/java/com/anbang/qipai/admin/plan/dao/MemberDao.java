package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Member;

public interface MemberDao {

	List<Member> queryByConditionsAndPage(int page, int size, Sort sort, String nickname);

	long getAmount();

	void addMember(Member member);

	Boolean deleteMember(String[] ids);

	Boolean editMember(Member member);
}
