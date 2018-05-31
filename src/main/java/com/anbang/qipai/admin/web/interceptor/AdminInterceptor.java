package com.anbang.qipai.admin.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.qipai.admin.web.vo.UserVo;

/**
 * 管理员操作拦截器
 * 
 * @author 林少聪 2018.5.31
 *
 */
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		Map<String, Boolean> map = user.getPrivilegeList();
		return verifyPrivilege(map, uri, response);
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

	private Boolean verifyPrivilege(Map<String, Boolean> map, String uri, HttpServletResponse response) {
		Boolean flag = false;
		String method = uri.substring(11);
		System.out.println("用户调用权限:" + method);
		if (method.equals("queryAdmin")) {
			flag = map.get("查询管理员");
		}
		if (method.equals("addAdmin")) {
			flag = map.get("增加管理员");
		}
		if (method.equals("deleteAdmin")) {
			flag = map.get("删除管理员");
		}
		if (method.equals("editAdmin")) {
			flag = map.get("修改管理员信息");
		}
		return flag;
	}
}
