package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.anbang.qipai.admin.plan.domain.Member;

public interface MemberDao {

	List<Member> queryByConditionsAndPage(Query query);

	long getAmount(Query query);

	void addMember(Member member);

	Boolean deleteMember(String[] ids);

	Boolean editMember(Member member);
}
