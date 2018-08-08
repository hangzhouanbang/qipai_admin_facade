package com.anbang.qipai.admin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private QipaiGameRomoteService qipaiGameRomoteService;

	@Autowired
	private GameService gameService;

	@RequestMapping("/servers_for_game")
	public CommonVO serversforgame(Game game) {
		CommonVO vo = new CommonVO();
		List<GameServer> serversForGame = gameService.findAllServersForGame(game);
		Map data = new HashMap();
		data.put("servers", serversForGame);
		vo.setData(data);
		return vo;
	}

	@RequestMapping("/game_server_online")
	public CommonVO gameserveronline(GameServer gameServer) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_gameServerOnline(gameServer);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping("/game_server_offline")
	public CommonVO gameserveroffline(String gameServerId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_gameServerOffline(gameServerId);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "/query_gamelaw")
	public CommonVO queryGameLaw(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, GameLaw law) {
		CommonVO vo = new CommonVO();
		ListPage listPage = gameService.findGameLawByConditions(page, size, law);
		vo.setMsg("gamelaw list");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/add_law")
	public CommonVO addlaw(GameLaw law) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_addLaw(law);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "/remove_law")
	public CommonVO removelaw(String lawId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_removelaw(lawId);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "/query_lawsmutexgroup")
	public CommonVO queryLawsMutexGroup(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, LawsMutexGroup lawsMutexGroup) {
		CommonVO vo = new CommonVO();
		ListPage listPage = gameService.findLawsMutexGroupByConditions(page, size, lawsMutexGroup);
		vo.setMsg("lawsMutexGroup list");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/add_mutexgroup")
	public CommonVO addmutexgroup(LawsMutexGroup lawsMutexGroup) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_addmutexgroup(lawsMutexGroup);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "/remove_mutexgroup")
	public CommonVO removemutexgroup(String groupId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_removemutexgroup(groupId);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

}
