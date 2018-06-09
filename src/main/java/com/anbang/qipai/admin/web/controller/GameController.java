package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.games.GameServer;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private QipaiGameRomoteService qipaiGameRomoteService;

	@RequestMapping("/game_server_online")
	@ResponseBody
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
	@ResponseBody
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

}
