package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.mongodb.WriteResult;

@Component
public class MongodbMemberDao implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<MemberDbo> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Query query = new Query();
		if (nickname != null) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, MemberDbo.class);
	}

	@Override
	public void addMember(MemberDbo member) {
		mongoTemplate.insert(member);
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, MemberDbo.class);
		System.out.println("删除了" + result.getN() + "个玩家");
		System.out.println("共要删除" + ids.length + "个玩家");
		return result.getN() <= ids.length;
	}

	@Override
	public void editMember(MemberDbo dbo) {
		Query query = new Query(Criteria.where("id").is(dbo.getId()));
		Update update = new Update();
		if (dbo.getVip() != null) {
			update.set("vip", dbo.getVip());
		}
		if (dbo.getGender() != null) {
			update.set("gender", dbo.getGender());
		}
		if (dbo.getGold() != null) {
			update.set("gold", dbo.getGold());
		}
		if (dbo.getHeadimgurl() != null) {
			update.set("headimgurl", dbo.getHeadimgurl());
		}
		if (dbo.getNickname() != null) {
			update.set("nickname", dbo.getNickname());
		}
		if (dbo.getPhone() != null) {
			update.set("phone", dbo.getPhone());
		}
		if (dbo.getScore() != null) {
			update.set("score", dbo.getScore());
		}
		if (dbo.getVipEndTime() != null) {
			update.set("vipEndTime", dbo.getVipEndTime());
		}
		if (dbo.getVipLevel() != null) {
			update.set("vipLevel", dbo.getVipLevel());
		}
		if (dbo.getVipScore() != null) {
			update.set("vipScore", dbo.getVipScore());
		}
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, MemberDbo.class);
	}

}
