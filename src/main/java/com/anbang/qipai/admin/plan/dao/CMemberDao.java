package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Member;

public interface CMemberDao {
	/**
	 * 根据姓名查询会员并分页
	 * 
	 * @param page
	 *            当前页
	 * @param size
	 *            每页数量
	 * @param nickname
	 *            会员昵称
	 * @return 结果集
	 */
	List<Member> queryByConditionsAndPage(int page, int size, Member member);
}
