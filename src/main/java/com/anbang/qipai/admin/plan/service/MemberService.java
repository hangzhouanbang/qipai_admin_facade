package com.anbang.qipai.admin.plan.service;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Member;

public interface MemberService {
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

	/**
	 * 添加会员
	 * 
	 * @param member
	 *            会员信息
	 */
	void addMember(Member member);

	/**
	 * 删除会员
	 * 
	 * @param ids
	 *            要删除的会员id数组
	 * @return 操作结果
	 */
	Boolean deleteMember(String[] ids);

	/**
	 * 编辑会员
	 * 
	 * @param member
	 *            会员信息
	 */
	void editMember(Member member);
}
