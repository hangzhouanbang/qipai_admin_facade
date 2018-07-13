package com.anbang.qipai.admin.web.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.service.permissionservice.PrivilegeService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.highto.framework.web.page.ListPage;

/**
 * 管理员权限controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping("/queryprivilege")
	public CommonVO queryPrivilege(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, String privilege) {
		CommonVO vo = new CommonVO();
		ListPage listPage = privilegeService.findPrivilegeByName(page, size, privilege);
		vo.setSuccess(true);
		vo.setMsg("privilegeList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/deployprivilege")
	public CommonVO deployPrivilege(@RequestParam(value = "privileges") String privileges) {
		CommonVO vo = new CommonVO();
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Privilege>>() {
		}.getType();
		ArrayList<Privilege> privilegeList = gson.fromJson(privileges, type);
		privilegeService.addPrivileges(privilegeList);
		vo.setSuccess(true);
		vo.setMsg("deploy privilege success");
		return vo;
	}

	@RequestMapping("/deleteprivilege")
	public CommonVO deletePrivilege(@RequestParam(value = "id") String[] ids) {
		CommonVO vo = new CommonVO();
		privilegeService.deletePrivilegeByIds(ids);
		vo.setSuccess(true);
		vo.setMsg("delete privilege success");
		return vo;
	}

	@RequestMapping("/updateprivilege")
	public CommonVO updatePrivilege(Privilege privilege) {
		CommonVO vo = new CommonVO();
		if (privilege.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("privilegeId is null");
			return vo;
		}
		privilegeService.updatePrivilege(privilege);
		vo.setSuccess(true);
		vo.setMsg("update privilege success");
		return vo;
	}

	@RequestMapping("/queryallprivilege")
	public CommonVO queryAllPrivilege() {
		CommonVO vo = new CommonVO();
		List<Privilege> privilegeList = privilegeService.findAllPrivileges();
		vo.setSuccess(true);
		vo.setMsg("privilegeList");
		vo.setData(privilegeList);
		return vo;
	}
}
