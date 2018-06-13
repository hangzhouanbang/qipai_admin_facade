package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.games.GameServer;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 游戏大厅远程服务
 * 
 * @author neo
 *
 */
@FeignClient("qipai-game")
public interface QipaiGameRomoteService {

	@RequestMapping(value = "/game/game_server_online")
	public CommonRemoteVO game_gameServerOnline(@RequestBody GameServer gameServer);

	@RequestMapping(value = "/game/game_server_offline")
	public CommonRemoteVO game_gameServerOffline(@RequestParam("gameServerId") String gameServerId);
	
	@RequestMapping(value = "/notice/addnotice")
	public CommonRemoteVO addNotice(@RequestParam(value = "notice") String notice);
	
	@RequestMapping(value = "/mail/addmail")
	public CommonRemoteVO addmail(@RequestParam(value = "mail") String mail);
	
	@RequestMapping(value = "/member/plan_rights_conf")
	public CommonRemoteVO commonuser(@RequestParam(value = "planMemberRoomsCount") int planMemberRoomsCount,@RequestParam(value = "planMemberRoomsAliveHours") int planMemberRoomsAliveHours,
			@RequestParam(value = "planMemberMaxCreateRoomDaily") int planMemberMaxCreateRoomDaily,@RequestParam(value = "planMemberCreateRoomDailyGoldPrice")
	int planMemberCreateRoomDailyGoldPrice,@RequestParam(value = "planMemberaddRoomDailyGoldPrice") int planMemberaddRoomDailyGoldPrice);
	
	@RequestMapping(value = "/member/vip_rights_conf")
	public CommonRemoteVO vipuser(@RequestParam(value = "vipMemberRoomsCount") int vipMemberRoomsCount,@RequestParam(value = "vipMemberRoomsAliveHours") int vipMemberRoomsAliveHours);

}