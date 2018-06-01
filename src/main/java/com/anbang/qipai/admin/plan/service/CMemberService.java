package com.anbang.qipai.admin.plan.service;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Member;

public interface CMemberService {
	/**
	 * 多条件查询并分页
	 * 
	 * @param page
	 *            当前页
	 * @param size
	 *            每页数量
	 * @param member
	 *            查询条件
	 * @return 结果集
	 */
	List<Member> queryByConditionsAndPage(int page, int size, Member member);
}
