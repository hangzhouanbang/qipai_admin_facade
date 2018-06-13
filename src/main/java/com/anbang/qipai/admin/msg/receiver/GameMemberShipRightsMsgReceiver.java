package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GameMemberShipRightsSink;
import com.anbang.qipai.admin.plan.domain.membershiprights.MemberShipRights;
import com.anbang.qipai.admin.plan.service.MemberShipRightsService;

import net.sf.json.JSONObject;

/**接收游戏大厅权益返回消息类
 * @author 程佳 2018.6.13
 * 这里的类的注解是使一个或多个接口作为参数
 * **/
@EnableBinding(GameMemberShipRightsSink.class)
public class GameMemberShipRightsMsgReceiver {

	@Autowired
	private MemberShipRightsService createMemberService;
	
	/**设置游戏大厅权益接收信息的方法
	 * @param payload 接收的json数据
	 * 这里的方法的注解做消息监听
	 * **/
	@StreamListener(GameMemberShipRightsSink.memberrightsconfiguration)
	public void members(Object payload) {
		System.out.println("payload:"+payload);
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		MemberShipRights commonuser = (MemberShipRights) JSONObject.toBean(obj, MemberShipRights.class);
		MemberShipRights commonusers = createMemberService.findallCommonUser();
		commonusers.setPlanMemberRoomsCount(commonuser.getPlanMemberRoomsCount());
		commonusers.setVipMemberRoomsCount(commonuser.getVipMemberRoomsCount());
		commonusers.setPlanMemberRoomsAliveHours(commonuser.getPlanMemberRoomsAliveHours());
		commonusers.setVipMemberRoomsAliveHours(commonuser.getVipMemberRoomsAliveHours());
		commonusers.setPlanMemberMaxCreateRoomDaily(commonuser.getPlanMemberMaxCreateRoomDaily());
		commonusers.setPlanMemberCreateRoomDailyGoldPrice(commonuser.getPlanMemberCreateRoomDailyGoldPrice());
		commonusers.setPlanMemberaddRoomDailyGoldPrice(commonuser.getPlanMemberaddRoomDailyGoldPrice());
		createMemberService.saveMemberShipRights(commonusers);
	}
}
