package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = memberDao.getAmount(new Query());
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		Query query = new Query();
		if (nickname != null) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		List<Member> memberList = memberDao.queryByConditionsAndPage(query);
		map.put("pageNumber", pageNumber);
		map.put("memberList", memberList);
		return map;
	}

	@Override
	public void addMember(Member member) {
		memberDao.addMember(member);
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		return memberDao.deleteMember(ids);
	}

	@Override
	public Boolean editMember(Member member) {
		return memberDao.editMember(member);
	}

}
