package com.anbang.qipai.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;

public class ExcelUtils {

	public static void generateOrderExcel(Integer rowid, Integer sheetNum, List<MemberOrder> list,
			XSSFWorkbook workbook) {
		XSSFSheet spreadsheet = null;
		if (rowid > 20000) {
			sheetNum++;
			spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
			rowid = 0;
		} else {
			spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
			XSSFRow row = spreadsheet.createRow(rowid);
			row.createCell(0).setCellValue("订单编号");
			row.createCell(1).setCellValue("支付方式");
			row.createCell(2).setCellValue("第三方订单号");
			row.createCell(3).setCellValue("支付状态");
			row.createCell(4).setCellValue("支付人编号");
			row.createCell(5).setCellValue("支付人昵称");
			row.createCell(6).setCellValue("收货人编号");
			row.createCell(7).setCellValue("收货人昵称");
			row.createCell(8).setCellValue("商品编号");
			row.createCell(9).setCellValue("商品名称");
			row.createCell(10).setCellValue("商品价格");
			row.createCell(11).setCellValue("购买数量");
			row.createCell(12).setCellValue("赠送玉石/张");
			row.createCell(13).setCellValue("赠送礼券/张");
			row.createCell(14).setCellValue("赠送会员时间/张");
			row.createCell(15).setCellValue("总价");
			row.createCell(16).setCellValue("购买ip地址");
			row.createCell(17).setCellValue("下单时间");
			row.createCell(18).setCellValue("发货时间");
			rowid++;
		}
		for (MemberOrder order : list) {
			XSSFRow row = spreadsheet.createRow(rowid);
			row.createCell(0).setCellValue(order.getId());
			row.createCell(1).setCellValue(order.getPay_type());
			row.createCell(2).setCellValue(order.getTransaction_id());
			row.createCell(3).setCellValue(order.getStatus());
			row.createCell(4).setCellValue(order.getPayerId());
			row.createCell(5).setCellValue(order.getPayerName());
			row.createCell(6).setCellValue(order.getReceiverId());
			row.createCell(7).setCellValue(order.getReceiverName());
			row.createCell(8).setCellValue(order.getProductId());
			row.createCell(9).setCellValue(order.getProductName());
			row.createCell(10).setCellValue(order.getProductPrice());
			row.createCell(11).setCellValue(order.getNumber());
			row.createCell(12).setCellValue(order.getGold());
			row.createCell(13).setCellValue(order.getScore());
			row.createCell(14).setCellValue(new SimpleDateFormat("DDD天").format(new Date(order.getVipTime())));
			row.createCell(15).setCellValue(order.getTotalamount());
			row.createCell(16).setCellValue(order.getReqIP());
			row.createCell(17)
					.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(order.getCreateTime())));
			row.createCell(18)
					.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(order.getDeliveTime())));
			rowid++;
		}
	}
}
