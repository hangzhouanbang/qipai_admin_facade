package com.anbang.qipai.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.entity.Admin;
import com.anbang.qipai.admin.service.AdminService;

@Controller
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	/**查询管理员并進行分頁
	 * @param page 当前页
	 * @param rows 每页显示数量
	 * @param nickName 管理员昵称
	 * @return 结果集
	 */
	@RequestMapping("/queryAdmin")
	@ResponseBody
	public List<Admin> queryAdmin(Integer page, Integer rows, String nickName) {
		System.out.println(nickName);
		List<Admin> list = null;
		if (nickName != null) {
			list = adminService.queryByNameAndPage(page, rows, nickName);
		} else {
			list = adminService.queryAllByPage(page, rows);
		}
		return list;
	}

	/**添加管理员
	 * @param admin 管理员信息
	 * @return 操作结果
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin(Admin admin) {
		System.out.println(admin);
		if (admin.getPass().isEmpty() || admin.getnickName().isEmpty() || admin.getUser().isEmpty()
				|| admin.getIdCard().isEmpty()) {
			return "fail";
		}
		Admin result = adminService.addAdmin(admin);
		System.out.println(result);
		if (result != null) {
			return "success";
		}
		return "fail";

	}

	/**删除管理员
	 * @param ids 要删除的管理员id数组
	 */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public void deleteAdmin(@RequestParam("id") String[] ids) {
		for (String s : ids) {
			System.out.println(s);
		}
		adminService.deleteAdmins(ids);
	}

	/**编辑管理员
	 * @param admin 编辑后的管理员信息
	 * @return 操作结果
	 */
	@RequestMapping("/editAdmin")
	@ResponseBody
	public String editAdmin(Admin admin) {
		System.out.println(admin);
		if ((admin.getPass().isEmpty() || admin.getnickName().isEmpty() || admin.getUser().isEmpty()
				|| admin.getIdCard().isEmpty()) && (admin.getId() != null)) {
			return "fail";
		}
		Admin result = adminService.editAdmin(admin);
		System.out.println(result);
		if (result != null) {
			return "success";
		}
		return "fail";
	}
}
