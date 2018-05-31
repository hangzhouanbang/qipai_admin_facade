package com.anbang.qipai.admin.plan.dao;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
/**设置金币service
 * @author 程佳 2018.5.31
 * **/
public interface MemberDao {
	
	/**设置金币
	 * @Param Member 赠送金币的对象信息
	 * **/
	CreateMemberConfiguration save(CreateMemberConfiguration member);

}
