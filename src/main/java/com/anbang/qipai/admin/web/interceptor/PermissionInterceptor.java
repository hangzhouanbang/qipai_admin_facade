package com.anbang.qipai.admin.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.qipai.admin.web.vo.PrivilegeVo;

/**
 * 管理员操作拦截器
 * 
 * @author 林少聪 2018.5.31
 *
 */
public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		@SuppressWarnings("unchecked")
		Map<String, PrivilegeVo> privilegevos = (Map<String, PrivilegeVo>) request.getSession()
				.getAttribute("privilegeList");
		return privilegevos.get(uri).getSelected();
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
