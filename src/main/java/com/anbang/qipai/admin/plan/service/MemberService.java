package com.anbang.qipai.admin.plan.service;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;

public interface MemberService {
	
	/**设置金币
	 * @Param member 赠送金币的数量
	 * **/
	CreateMemberConfiguration save(Integer goldForNewMember);

}
