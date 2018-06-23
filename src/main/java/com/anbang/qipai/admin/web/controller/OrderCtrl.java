package com.anbang.qipai.admin.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.OrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.OrderVO;
import com.highto.framework.web.page.ListPage;

@RestController
@RequestMapping("/order")
public class OrderCtrl {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/queryorder")
	public CommonVO queryOrder(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, OrderVO order) {
		CommonVO vo = new CommonVO();
		System.out.println(order);
		Sort sort = new Sort(new Order("id"));
		ListPage listPage = orderService.findOrder(page.intValue(), size.intValue(), sort, order);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/download")
	public void downLoad(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		// 第一步：设置响应类型
		resp.setContentType("application/force-download");// 应用程序强制下载
		// 第二读取文件
		String path = req.getServletContext().getRealPath("/111.xlsx");
		InputStream in = new FileInputStream(path);
		// 设置响应头，对文件进行url编码
		String name = URLEncoder.encode("111.xlsx", "UTF-8");
		resp.setHeader("Content-Disposition", "attachment;filename=" + name);
		resp.setContentLength(in.available());

		// 第三步：老套路，开始copy
		OutputStream out = resp.getOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		out.flush();
		out.close();
		in.close();
	}
}
