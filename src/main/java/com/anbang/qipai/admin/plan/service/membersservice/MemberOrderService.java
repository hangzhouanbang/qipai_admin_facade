package com.anbang.qipai.admin.plan.service.membersservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberOrderDao;
import com.anbang.qipai.admin.util.ExcelUtils;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOrderVO;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberOrderService {

	@Autowired
	private MemberOrderDao orderDao;

	public void addOrder(MemberOrder order) {
		orderDao.addOrder(order);
	}

	public void orderFinished(String id, String transaction_id, String status, long deliveTime) {
		orderDao.orderFinished(id, transaction_id, status, deliveTime);
	}

	public ListPage findOrderByConditions(int page, int size, MemberOrderVO order) {
		long amount = orderDao.getAmountByConditions(order);
		List<MemberOrder> orderList = orderDao.findOrderByConditions(page, size, order);
		ListPage listPage = new ListPage(orderList, page, size, (int) amount);
		return listPage;
	}

	public double countCostByTime(long startTime, long endTime) {
		return orderDao.countCostByTime(startTime, endTime);
	}

	public String exportOrder(MemberOrderVO order) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xls";
		Integer rowid = 0;
		Integer sheetNum = 1;
		XSSFWorkbook workbook = new XSSFWorkbook();
		File file = new File("src/main/webapp/excel");
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream output = new FileOutputStream(file + "/" + fileName);
		long amount = orderDao.getAmountByConditions(order);
		int size = 300;
		long pageNum = amount % size > 0 ? amount / size + 1 : amount / size;
		for (int page = 1; page <= pageNum; page++) {
			List<MemberOrder> orderList = orderDao.findOrderByConditions(page, size, order);
			ExcelUtils.generateOrderExcel(rowid, sheetNum, orderList, workbook);
		}
		workbook.write(output);
		workbook.close();
		output.close();
		return fileName;
	}

	// public void exportOrder(MemberOrderVO order, OutputStream output) throws
	// IOException {
	// Integer rowid = 0;
	// Integer sheetNum = 1;
	// XSSFWorkbook workbook = new XSSFWorkbook();
	// long amount = orderDao.getAmountByConditions(order);
	// int size = 300;
	// long pageNum = amount % size > 0 ? amount / size + 1 : amount / size;
	// for (int page = 1; page <= pageNum; page++) {
	// List<MemberOrder> orderList = orderDao.findOrderByConditions(page, size,
	// order);
	// ExcelUtils.generateOrderExcel(rowid, sheetNum, orderList, workbook);
	// }
	// workbook.write(output);
	// workbook.close();
	// output.close();
	// }
}
