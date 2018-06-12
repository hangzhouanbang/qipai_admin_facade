package com.anbang.qipai.admin.plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.membershiprightsdao.MemberShipRightsDao;
import com.anbang.qipai.admin.plan.domain.membershiprights.CommonUser;
import com.anbang.qipai.admin.plan.domain.membershiprights.VipUser;

@Service
public class MemberShipRightsService{

	@Autowired
	private MemberShipRightsDao memberDao;

	public CommonUser commonsave(CommonUser commonuser) {
		return memberDao.commonsave(commonuser);
	}

	public VipUser vipuser(VipUser vipuser) {
		return memberDao.vipuser(vipuser);
	}
	
	
}
