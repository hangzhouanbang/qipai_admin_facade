package com.anbang.qipai.admin.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOrderVO;
import com.highto.framework.web.page.ListPage;

/**
 * 充值记录controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class MemberOrderController {
	@Autowired
	private MemberOrderService orderService;

	/**
	 * 查询充值记录
	 * 
	 * @param page
	 * @param size
	 * @param order
	 * @return
	 */
	@RequestMapping("/queryorder")
	public CommonVO queryOrder(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, MemberOrderVO order) {
		CommonVO vo = new CommonVO();
		ListPage listPage = orderService.findOrderByConditions(page, size, order);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 充值记录导出、未及时删除文件
	 * 
	 * @param order
	 * @return
	 */
	// @RequestMapping("/download")
	// public CommonVO downLoad(MemberOrderVO order) {
	// CommonVO vo = new CommonVO();
	// String fileName = "";
	// try {
	// fileName = orderService.exportOrder(order);
	// } catch (IOException e) {
	// vo.setSuccess(false);
	// vo.setMsg("IOException");
	// }
	// vo.setSuccess(true);
	// vo.setMsg("orderList");
	// vo.setData("/excel/" + fileName);
	// return vo;
	// }

	// 备选方案,因为ajax无法下载文件
	@RequestMapping("/download")
	public CommonVO downLoad(MemberOrderVO order, HttpServletResponse response) {
		CommonVO vo = new CommonVO();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xls";
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/msexcel");
		try {
			OutputStream output = response.getOutputStream();
			orderService.exportOrder(order, output);
		} catch (IOException e) {
			vo.setSuccess(false);
			vo.setMsg("IOException");
		}
		vo.setSuccess(true);
		vo.setMsg("orderList");
		return vo;
	}
}
