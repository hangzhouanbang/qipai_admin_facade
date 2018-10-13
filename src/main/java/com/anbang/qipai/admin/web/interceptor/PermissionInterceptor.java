package com.anbang.qipai.admin.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;

/**
 * 管理员操作拦截器
 * 
 * @author 林少聪 2018.5.31
 *
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Autowired
	private AdminAuthService adminAuthService;

	@Autowired
	private AdminService adminService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		if (token == null) {
			return false;
		}
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId == null) {
			return false;
		}
		String uri = request.getRequestURI();
		Admin admin = adminService.findAdminById(adminId);
		List<Role> roleList = admin.getRoleList();
		for (Role role : roleList) {
			List<Privilege> privilegeList = role.getPrivilegeList();
			for (Privilege p : privilegeList) {
				if (uri.equals(p.getUri())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
