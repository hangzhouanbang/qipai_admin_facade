package com.anbang.qipai.admin.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.service.PrivilegeService;

import net.sf.json.JSONArray;

/**
 * 管理员权限controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@RestController
@RequestMapping("/privilegeCtrl")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping("/queryPrivilege")
	public Map<String, Object> queryPrivilege(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String privilege) {
		Map<String, Object> map = privilegeService.findByPrivilege(page, size, privilege);
		return map;
	}

	@RequestMapping("/deployPrivilege")
	public String deployPrivilege(@RequestParam(name = "privileges") String privileges) {
		JSONArray ja = JSONArray.fromObject(privileges);
		List<Privilege> privilegeList = new ArrayList<Privilege>();
		for (int i = 0; i < ja.size(); i++) {
			Privilege privilege = new Privilege();
			privilege.setPrivilege(ja.getJSONObject(i).getString("privilege"));
			privilege.setUri(ja.getJSONObject(i).getString("uri"));
			privilegeList.add(privilege);
		}
		if (privilegeService.addPrivileges(privilegeList)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/deletePrivilege")
	public String deletePrivilege(@RequestParam(name = "id") String[] ids) {
		privilegeService.deletePrivileges(ids);
		return "success";
	}

	@RequestMapping("/editPrivilege")
	public String editPrivilege(Privilege privilege) {
		if (privilege.getId() != null && privilegeService.updatePrivilege(privilege)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/queryAllPrivilege")
	public List<Privilege> queryAllPrivilege() {
		return privilegeService.findAllPrivileges();
	}
}
