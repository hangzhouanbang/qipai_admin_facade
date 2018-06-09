package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.service.PrivilegeService;

@RestController
@RequestMapping("/privilegeCtrl")
public class PrivilegeCtrl {

	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping("/queryPrivilege")
	public Map<String, Object> queryPrivilege(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String privilege) {
		Sort sort = new Sort(new Order("id"));
		Map<String, Object> map = privilegeService.queryByConditionsAndPage(page.intValue(), size.intValue(), sort,
				privilege);
		return map;
	}

	@RequestMapping("/deployPrivilege")
	public String deployPrivilege(@RequestParam(name = "privilege") Privilege[] privileges) {
		if (privilegeService.addPrivileges(privileges)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/deletePrivilege")
	public void deletePrivilege(@RequestParam(name = "id") String[] ids) {
		privilegeService.deletePrivileges(ids);
	}

	@RequestMapping("/editPrivilege")
	public String editPrivilege(Privilege privilege) {
		if (privilege.getId() != null || privilegeService.editPrivilege(privilege)) {
			return "success";
		}
		return "fail";
	}
}
