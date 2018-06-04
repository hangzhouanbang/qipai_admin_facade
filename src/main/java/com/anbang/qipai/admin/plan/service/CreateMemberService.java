package com.anbang.qipai.admin.plan.service;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
/**设置金币service
 * @author 程佳 2018.5.31
 * **/
public interface CreateMemberService {
	
	/**设置金币
	 * @Param member 赠送金币的数量
	 * **/
	CreateMemberConfiguration save(CreateMemberConfiguration member);

}
