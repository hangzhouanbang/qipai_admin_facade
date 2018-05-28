package com.anbang.qipai.admin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.dao.mongodb.repository.AdminRepository;
import com.anbang.qipai.admin.entity.Admin;
import com.anbang.qipai.admin.service.AdminService;

@Controller
public class AdminCtrl {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/queryAdmin")
	@ResponseBody
	public List<Admin> queryAdmin(Integer page, Integer rows, String name) {
		List<Admin> list = adminService.queryByNameAndPage(page, rows, name);
		for (Admin a : list) {
			System.out.println(a);
		}
		return list;
	}

	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin(String user,String pass,String name,String idcard,Integer sex) {
		if(name!=null || !name.equals("") && pass!=null || !pass.equals("")) 
		{
			Admin admin=new Admin();
			admin.setName(name);
			admin.setPass(pass);
			admin.setUser(user);
			admin.setSex(sex);
			admin.setIdCard(idcard);
			admin.setcreateTime(new Date());
			adminService.addAdmin(admin);
			return "200";
		}
		
		return "0";
	}

	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public String deleteAdmin(String id) {
		adminService.deleteAdmin(id);
		return "200";
	}
	
	@RequestMapping("/toeditAdmin")
	@ResponseBody
	public Admin toeditAdmin(String id) {
		Admin admin=adminService.toeditAdmin(id);
		return admin;
	}

	@RequestMapping("/editAdmin")
	@ResponseBody
	public String editAdmin(String id,String user,String pass,String name,String idcard,Integer sex) {
		Admin admin=new Admin();
		admin.setId(id);
		admin.setName(name);
		admin.setPass(pass);
		admin.setUser(user);
		admin.setSex(sex);
		admin.setIdCard(idcard);
		adminService.editAdmin(admin);
		return "200";
	}
}
