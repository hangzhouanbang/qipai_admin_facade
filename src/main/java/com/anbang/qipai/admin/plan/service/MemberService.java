package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Member;

public interface MemberService {

	Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname);

	void addMember(Member member);

	Boolean deleteMember(String[] ids);

	Boolean editMember(Member member);
}
