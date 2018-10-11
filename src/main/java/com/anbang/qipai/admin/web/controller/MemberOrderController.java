package com.anbang.qipai.admin.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/queryorder", method = RequestMethod.POST)
	public CommonVO queryOrder(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, MemberOrderVO order) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap<>();
		ListPage listPage = orderService.findOrderByConditions(page, size, order);
		data.put("listPage", listPage);
		double payCost = orderService.countPayCostByConditions(order);
		data.put("payCost", payCost);
		int payAmount = orderService.countPayAmountByConditions(order);
		data.put("payAmount", payAmount);
		double notPayCost = orderService.countNotPayCostByConditions(order);
		data.put("notPayCost", notPayCost);
		int notPayAmount = orderService.countNotPayAmountByConditions(order);
		data.put("notPayAmount", notPayAmount);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(data);
		return vo;
	}

	@RequestMapping(value = "/queryrecharge", method = RequestMethod.POST)
	public CommonVO queryCharge(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, MemberOrderVO order) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap<>();
		order.setStatus("PAYSUCCESS");
		ListPage listPage = orderService.findOrderByConditions(page, size, order);
		data.put("listPage", listPage);
		double cost = orderService.countCost();
		data.put("cost", cost);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(data);
		return vo;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public CommonVO downLoad(MemberOrderVO order, HttpServletResponse response) {
		CommonVO vo = new CommonVO();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xlsx";
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/msexcel");
		try {
			OutputStream output = response.getOutputStream();
			orderService.exportOrder(order, output);
			output.close();
		} catch (IOException e) {
			vo.setSuccess(false);
			vo.setMsg("IOException");
		}
		vo.setSuccess(true);
		vo.setMsg("orderList");
		return vo;
	}

	@RequestMapping(value = "/rechargedownload", method = RequestMethod.GET)
	public CommonVO rechargeDownLoad(MemberOrderVO order, HttpServletResponse response) {
		CommonVO vo = new CommonVO();
		order.setStatus("PAYSUCCESS");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xlsx";
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/msexcel");
		try {
			OutputStream output = response.getOutputStream();
			orderService.exportOrder(order, output);
			output.close();
		} catch (IOException e) {
			vo.setSuccess(false);
			vo.setMsg("IOException");
		}
		vo.setSuccess(true);
		vo.setMsg("orderList");
		return vo;
	}
}
