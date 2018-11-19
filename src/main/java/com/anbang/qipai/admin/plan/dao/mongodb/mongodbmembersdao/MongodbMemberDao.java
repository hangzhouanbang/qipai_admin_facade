package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;

@Component
public class MongodbMemberDao implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<MemberDbo> findMemberDboByConditions(int page, int size, MemberVO member) {
		Query query = new Query();
		if (member.getId() != null && !"".equals(member.getId())) {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && !"".equals(member.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getOnlineState() != null && !"".equals(member.getOnlineState())) {
			query.addCriteria(Criteria.where("onlineState").is(member.getOnlineState()));
		}
		if ("true".equals(member.getIsVip()) || "false".equals(member.getIsVip())) {
			query.addCriteria(Criteria.where("vip").is(Boolean.valueOf(member.getIsVip())));
		}
		query.with(member.getSort());
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, MemberDbo.class);
	}

	@Override
	public long getAmountByConditions(MemberVO member) {
		Query query = new Query();
		if (member.getId() != null && !"".equals(member.getId())) {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && !"".equals(member.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getOnlineState() != null && !"".equals(member.getOnlineState())) {
			query.addCriteria(Criteria.where("onlineState").is(member.getOnlineState()));
		}
		if ("true".equals(member.getIsVip()) || "false".equals(member.getIsVip())) {
			query.addCriteria(Criteria.where("vip").is(Boolean.valueOf(member.getIsVip())));
		}
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public void addMember(MemberDbo member) {
		mongoTemplate.insert(member);
	}

	@Override
	public MemberDbo findMemberById(String memberId) {
		return mongoTemplate.findById(memberId, MemberDbo.class);
	}

	@Override
	public long countNewMemberByTime(long startTime, long endTime) {
		Query query = new Query(Criteria.where("createTime").gte(startTime).lt(endTime));
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public long countVipMember() {
		Query query = new Query(Criteria.where("vip").is(true));
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public void updateMemberPhone(String memberId, String phone) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("phone", phone);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberVip(String memberId, boolean vip) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vip", vip);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void memberOrderDelive(String memberId, boolean vip, long vipEndTime, int vipLevel, double vipScore,
			double cost) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vipEndTime", vipEndTime);
		update.set("vip", vip);
		update.set("vipLevel", vipLevel);
		update.set("vipScore", vipScore);
		update.set("cost", cost);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberRealUser(String memberId, String realName, String IDcard, boolean verify) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("realName", realName);
		update.set("idCard", IDcard);
		update.set("verifyUser", verify);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void rechargeVip(String memberId, boolean vip, long vipEndTime) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vipEndTime", vipEndTime);
		update.set("vip", vip);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberOnlineState(String memberId, String onlineState) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("onlineState", onlineState);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public List<String> findAllMemberId() {
		Query query = new Query();
		List<MemberDbo> memberDboList = mongoTemplate.find(query, MemberDbo.class);
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < memberDboList.size(); i++) {
			ids.add(memberDboList.get(i).getId());
		}
		return ids;
	}

	@Override
	public List<String> findMemberId() {
		Query query = new Query(Criteria.where("vip").is(false));
		List<MemberDbo> memberDboList = mongoTemplate.find(query, MemberDbo.class);
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < memberDboList.size(); i++) {
			ids.add(memberDboList.get(i).getId());
		}
		return ids;
	}

	@Override
	public List<String> findVipMemberId() {
		Query query = new Query(Criteria.where("vip").is(true));
		List<MemberDbo> memberDboList = mongoTemplate.find(query, MemberDbo.class);
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < memberDboList.size(); i++) {
			ids.add(memberDboList.get(i).getId());
		}
		return ids;
	}

	@Override
	public void updateMemberGold(String memberId, int gold) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("gold", gold);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberScore(String memberId, int score) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("score", score);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberCost(String memberId, double cost) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("cost", cost);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public long countAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public void updateMemberBindAgent(String memberId, String agentId, boolean bindAgent) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("bindAgent", bindAgent);
		update.set("agentId", agentId);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public List<MemberDbo> findMemberDboByIds(String[] memberIds) {
		Object[] ids = memberIds;
		Query query = new Query(Criteria.where("id").in(ids));
		return mongoTemplate.find(query, MemberDbo.class);
	}

	@Override
	public void removeMemberBindAgent(String memberId) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("bindAgent", false);
		update.unset("agentId");
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

}
