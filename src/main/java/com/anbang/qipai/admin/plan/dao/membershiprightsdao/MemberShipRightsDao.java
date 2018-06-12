package com.anbang.qipai.admin.plan.dao.membershiprightsdao;

import com.anbang.qipai.admin.plan.domain.membershiprights.CommonUser;
import com.anbang.qipai.admin.plan.domain.membershiprights.VipUser;
/**会员权益service
 * @author 程佳 2018.5.31
 * **/
public interface MemberShipRightsDao {
	
	/**普通会员权益
	 * @Param commonuser 普通会员权益信息 
	 * **/
	CommonUser commonsave(CommonUser commonuser);
	
	/**vip会员权益
	 * @Param member vip会员权益信息
	 * **/
	VipUser vipuser(VipUser vipuser);

}
