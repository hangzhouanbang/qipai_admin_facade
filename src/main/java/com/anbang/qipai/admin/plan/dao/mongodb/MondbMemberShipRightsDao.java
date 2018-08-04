package com.anbang.qipai.admin.plan.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.membershiprights.MemberShipRights;
import com.anbang.qipai.admin.plan.dao.membershiprightsdao.MemberShipRightsDao;

@Component
public class MondbMemberShipRightsDao implements MemberShipRightsDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public MemberShipRights saveMemberShipRights(MemberShipRights commonuser) {
		mongoTemplate.save(commonuser);
		return commonuser;
	}

	@Override
	public MemberShipRights findallCommonUser() {
		return mongoTemplate.findById("1", MemberShipRights.class);
	}
	

}
