package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MemberShipRightsSink;
import com.anbang.qipai.admin.plan.domain.membershiprights.MemberShipRights;
import com.anbang.qipai.admin.plan.service.MemberShipRightsService;

import net.sf.json.JSONObject;

/**接收会员大厅权益返回消息类
 * @author 程佳 2018.6.1
 * 这里的类的注解是使一个或多个接口作为参数
 * **/
@EnableBinding(MemberShipRightsSink.class)
public class MemberShipRightsMsgReceiver {
	
	@Autowired
	private MemberShipRightsService createMemberService;
	
	/**设置会员大厅权益接收信息的方法
	 * @param payload 接收的json数据
	 * 这里的方法的注解做消息监听
	 * **/
	@StreamListener(MemberShipRightsSink.membershiprights)
	public void members(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		MemberShipRights commonuser = (MemberShipRights) JSONObject.toBean(obj, MemberShipRights.class);
		MemberShipRights commonusers = createMemberService.findallCommonUser();
		System.out.println("payload:"+payload+commonuser.getSignGoldNumber()+commonuser.getGoldForNewNember());
		commonusers.setSignGoldNumber(commonuser.getSignGoldNumber());
		commonusers.setGoldForNewNember(commonuser.getGoldForNewNember());
		commonusers.setShareIntegralNumber(commonuser.getShareIntegralNumber());
		commonusers.setShareGoldNumber(commonuser.getShareGoldNumber());
		commonusers.setInviteIntegralNumber(commonuser.getInviteIntegralNumber());
		commonusers.setVipGrowIntegralSpeed(commonuser.getVipGrowIntegralSpeed());
		commonusers.setPlanGrowIntegralSpeed(commonuser.getPlanGrowIntegralSpeed());
		commonusers.setVipGrowGradeSpeed(commonuser.getVipGrowGradeSpeed());
		createMemberService.saveMemberShipRights(commonusers);
	}
	
	

}
