package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.ArrayList;
import java.util.List;

import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;
import com.anbang.qipai.admin.plan.dao.hongbaodao.MemberInvitationRecordDao;
import com.anbang.qipai.admin.plan.dao.reportdao.OnlineStateRecordDao;
import com.anbang.qipai.admin.web.query.MemberQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOnlineState;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberDboService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private OnlineStateRecordDao onlineStateRecordDao;

	@Autowired
	private MemberInvitationRecordDao memberInvitationRecordDao;

	public List<MemberDbo> findMemberDboByIds(String[] memberIds) {
		return memberDao.findMemberDboByIds(memberIds);
	}

	public ListPage findMemberDboByConditions(int page, int size, MemberVO member) {
		long amount = memberDao.getAmountByConditions(member);
		List<MemberDbo> memberList = memberDao.findMemberDboByConditions(page, size, member);
		for (int i = 0; i < memberList.size(); i++) {
			String onlineState = memberList.get(i).getOnlineState();
			memberList.get(i).setOnlineState(MemberOnlineState.getSummaryText(onlineState));
		}

		List<MemberVO> memberVOs = new ArrayList<>();
		for (MemberDbo list : memberList) {
			MemberVO vo = new MemberVO();
			BeanUtils.copyProperties(list, vo);
			int totalInvitationNum = (int) memberInvitationRecordDao.countByMemberId(list.getId(), null);
			vo.setTotalInvitationNum(totalInvitationNum);
			memberVOs.add(vo);
		}

		ListPage listPage = new ListPage(memberVOs, page, size, (int) amount);
		return listPage;
	}

	public ListPage findMemberDboByQuery(int page, int size, MemberQuery query) {
		if (CollectionUtils.isEmpty(query.getIds())) {
			return new ListPage(new ArrayList<>(), page,size, 0);
		}

		long amount = memberDao.countByQuery(query);
		List<MemberDbo> memberList = memberDao.findMemberDboByQuery(page, size, query);
		List<MemberVO> memberVOS = new ArrayList<>();
		for (MemberDbo list : memberList) {
			String onlineState = list.getOnlineState();
			list.setOnlineState(MemberOnlineState.getSummaryText(onlineState));
			// 获取最后一次登录时间
			OnlineStateRecord onlineStateRecord = onlineStateRecordDao.lastRecord(list.getId());
			MemberVO vo = new MemberVO();
			BeanUtils.copyProperties(list,vo);
			if (onlineStateRecord != null) {
				vo.setLastLoginTime(onlineStateRecord.getCreateTime());
			}
			memberVOS.add(vo);
		}
		ListPage listPage = new ListPage(memberVOS, page, size, (int) amount);
		return listPage;
	}

	public long countMemberDboByQuery(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return 0;
		}
		MemberQuery query = new MemberQuery();
		query.setIds(ids);
		long amount = memberDao.countByQuery(query);
		return amount;
	}

	public long countAmount() {
		return memberDao.countAmount();
	}


	public void addMember(MemberDbo member) {
		memberDao.addMember(member);
	}

	public MemberDbo findMemberById(String memberId) {
		return memberDao.findMemberById(memberId);
	}

	public void updateMemberPhone(MemberDbo member) {
		memberDao.updateMemberPhone(member.getId(), member.getPhone());
	}

	public void updateMemberBaseInfo(MemberDbo member) {
		memberDao.updateMemberBaseInfo(member.getId(), member.getNickname(), member.getHeadimgurl(),
				member.getGender());
	}

	public void updateMemberVip(MemberDbo member) {
		memberDao.updateMemberVip(member.getId(), member.isVip());
	}

	public void updateMemberCost(MemberDbo member) {
		memberDao.updateMemberCost(member.getId(), member.getCost());
	}

	public void updateMemberGold(String memberId, int gold) {
		memberDao.updateMemberGold(memberId, gold);
	}

	public void updateMemberScore(String memberId, int score) {
		memberDao.updateMemberScore(memberId, score);
	}

	public void memberOrderDelive(MemberDbo member) {
		memberDao.memberOrderDelive(member.getId(), member.isVip(), member.getVipEndTime(), member.getVipLevel(),
				member.getVipScore(), member.getCost());
	}

	public void updateVip(String memberId, boolean vip, long endTime) {
		memberDao.rechargeVip(memberId, vip, endTime);
	}

	public void rechargeVip(MemberDbo member) {
		memberDao.rechargeVip(member.getId(), member.isVip(), member.getVipEndTime());
	}

	public long countNewMemberByTime(long startTime, long endTime) {
		return memberDao.countNewMemberByTime(startTime, endTime);
	}

	public long countVipMember() {
		return memberDao.countVipMember();
	}

	public void updateMemberRealUser(MemberDbo member) {
		memberDao.updateMemberRealUser(member.getId(), member.getRealName(), member.getIdCard(), member.isVerifyUser());
	}

	public void updateMemberBindAgent(String memberId, String agentId, boolean bindAgent) {
		memberDao.updateMemberBindAgent(memberId, agentId, bindAgent);
	}

	public void removeMemberBindAgent(String memberId) {
		memberDao.removeMemberBindAgent(memberId);
	}

	public void updateMemberOnlineState(String memberId, String onlineState) {
		memberDao.updateMemberOnlineState(memberId, onlineState);
	}

	public void updateOnlineStateAndIP(String memberId, String onlineState, String loginIp, String ipAddress) {
		memberDao.updateOnlineStateAndIP(memberId, onlineState, loginIp, ipAddress);
	}

	public List<String> findVipMemberId() {
		List<String> ids = memberDao.findVipMemberId();
		return ids;
	}

	public List<String> findMemberId() {
		List<String> ids = memberDao.findMemberId();
		return ids;
	}

	public List<String> findAllMemberId() {
		List<String> ids = memberDao.findAllMemberId();
		return ids;
	}

	public List<MemberDbo> findMemberAfterTime(long startTime) {
		return memberDao.findMemberAfterTime(startTime);
	}

	public Integer countOnlineState() {
		return (int) memberDao.countOnlineState();
	}

	public long countRobotAmount() {
		return memberDao.countRobotAmount();
	}

	public long countRobotVipMember() {
		return memberDao.countRobotVipMember();
	}

	public List<MemberDbo> findMemberByIP(String loginIp) {
		return memberDao.findMemberByIP(loginIp);
	}
}
