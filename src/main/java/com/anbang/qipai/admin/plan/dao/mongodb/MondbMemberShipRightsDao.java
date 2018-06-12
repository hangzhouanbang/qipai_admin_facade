package com.anbang.qipai.admin.plan.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.membershiprightsdao.MemberShipRightsDao;
import com.anbang.qipai.admin.plan.domain.membershiprights.CommonUser;
import com.anbang.qipai.admin.plan.domain.membershiprights.VipUser;

@Component
public class MondbMemberShipRightsDao implements MemberShipRightsDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public CommonUser commonsave(CommonUser commonuser) {
		mongoTemplate.save(commonuser);
		return commonuser;
	}

	@Override
	public VipUser vipuser(VipUser vipuser) {
		mongoTemplate.save(vipuser);
		return vipuser;
	}
	

}
