package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
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
	public CommonRemoteVO game_gameServerOffline(@RequestBody String[] gameServerIds);

	@RequestMapping(value = "/game/add_law")
	public CommonRemoteVO game_addLaw(@RequestBody GameLaw law);

	@RequestMapping(value = "/game/remove_law")
	public CommonRemoteVO game_removelaw(@RequestParam("lawId") String lawId);

	@RequestMapping(value = "/game/add_mutexgroup")
	public CommonRemoteVO game_addmutexgroup(@RequestBody LawsMutexGroup lawsMutexGroup);

	@RequestMapping(value = "/game/remove_mutexgroup")
	public CommonRemoteVO game_removemutexgroup(@RequestParam("groupId") String groupId);

	@RequestMapping(value = "/notice/addnotice")
	public CommonRemoteVO addNotice(@RequestParam(value = "notice") String notice,
			@RequestParam(value = "place") String place, @RequestParam(value = "adminname") String adminname);

	@RequestMapping(value = "/notice/updatenotice")
	public CommonRemoteVO updateNotice(@RequestParam(value = "id") String id);

	@RequestMapping(value = "/mail/addmail")
	public CommonRemoteVO addmail(@RequestParam(value = "mail") String mail);

	@RequestMapping(value = "/mail/addmailbyid")
	public CommonRemoteVO addMailById(@RequestParam(value = "mail") String mail, @RequestBody String[] ids);

	@RequestMapping(value = "/member/plan_rights_conf")
	public CommonRemoteVO commonuser(@RequestParam(value = "planMemberRoomsCount") int planMemberRoomsCount,
			@RequestParam(value = "planMemberRoomsAliveHours") int planMemberRoomsAliveHours,
			@RequestParam(value = "planMemberMaxCreateRoomDaily") int planMemberMaxCreateRoomDaily,
			@RequestParam(value = "planMemberCreateRoomDailyGoldPrice") int planMemberCreateRoomDailyGoldPrice,
			@RequestParam(value = "planMemberJoinRoomGoldPrice") int planMemberJoinRoomGoldPrice);

	@RequestMapping(value = "/member/vip_rights_conf")
	public CommonRemoteVO vipuser(@RequestParam(value = "vipMemberRoomsCount") int vipMemberRoomsCount,
			@RequestParam(value = "vipMemberRoomsAliveHours") int vipMemberRoomsAliveHours);

}
