package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.remote.service.QipaiChaguanRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;

@CrossOrigin
@RestController
@RequestMapping("/chaguan")
public class ChaguanController {

	@Autowired
	private QipaiChaguanRemoteService qipaiChaguanRemoteService;

	@RequestMapping("/apply_pass")
	public CommonVO chaguanApplyPass(String applyId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiChaguanRemoteService.agentchaguan_apply_pass(applyId);
		vo.setSuccess(rvo.isSuccess());
		vo.setMsg(rvo.getMsg());
		vo.setData(rvo.getData());
		return vo;
	}

	@RequestMapping("/apply_refuse")
	public CommonVO chaguanApplyRefuse(String applyId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiChaguanRemoteService.agentchaguan_apply_refuse(applyId);
		vo.setSuccess(rvo.isSuccess());
		vo.setMsg(rvo.getMsg());
		vo.setData(rvo.getData());
		return vo;
	}
}
