package com.anbang.qipai.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;

public class ExcelUtils {

	// public static void generateOrderExcel(Integer rowid, Integer sheetNum,
	// List<MemberOrder> list,
	// XSSFWorkbook workbook) {
	// XSSFSheet spreadsheet = null;
	// if (rowid > 20000) {
	// sheetNum++;
	// spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
	// rowid = 0;
	// } else {
	// spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
	// XSSFRow row = spreadsheet.createRow(rowid);
	// row.createCell(0).setCellValue("订单编号");
	// row.createCell(1).setCellValue("支付方式");
	// row.createCell(2).setCellValue("第三方订单号");
	// row.createCell(3).setCellValue("支付状态");
	// row.createCell(4).setCellValue("会员编号");
	// row.createCell(5).setCellValue("会员昵称");
	// row.createCell(6).setCellValue("会员卡id");
	// row.createCell(7).setCellValue("会员卡名字");
	// row.createCell(8).setCellValue("会员卡价格");
	// row.createCell(9).setCellValue("购买数量");
	// row.createCell(10).setCellValue("赠送金币/张");
	// row.createCell(11).setCellValue("赠送积分/张");
	// row.createCell(12).setCellValue("赠送会员时间/张");
	// row.createCell(13).setCellValue("总价");
	// row.createCell(14).setCellValue("购买ip地址");
	// row.createCell(15).setCellValue("下单时间");
	// row.createCell(16).setCellValue("发货时间");
	// rowid++;
	// }
	// for (MemberOrder order : list) {
	// XSSFRow row = spreadsheet.createRow(rowid);
	// row.createCell(0).setCellValue(order.getOut_trade_no());
	// row.createCell(1).setCellValue(order.getPay_type());
	// row.createCell(2).setCellValue(order.getTransaction_id());
	// row.createCell(3).setCellValue(order.getStatus());
	// row.createCell(4).setCellValue(order.getMemberId());
	// row.createCell(5).setCellValue(order.getNickname());
	// row.createCell(6).setCellValue(order.getClubCardId());
	// row.createCell(7).setCellValue(order.getClubCardName());
	// row.createCell(8).setCellValue(order.getClubCardPrice());
	// row.createCell(9).setCellValue(order.getNumber());
	// row.createCell(10).setCellValue(order.getGold());
	// row.createCell(11).setCellValue(order.getScore());
	// if (order.getVipTime() != null) {
	// SimpleDateFormat format = new SimpleDateFormat("DDD天");
	// Date date = new Date(order.getVipTime());
	// row.createCell(12).setCellValue(format.format(date));
	// }
	// row.createCell(13).setCellValue(order.getTotalamount());
	// row.createCell(14).setCellValue(order.getReqIP());
	// if (order.getCreateTime() != null) {
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = new Date(order.getCreateTime());
	// row.createCell(15).setCellValue(format.format(date));
	// }
	// if (order.getDeliveTime() != null) {
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = new Date(order.getDeliveTime());
	// row.createCell(16).setCellValue(format.format(date));
	// }
	// rowid++;
	// }
	// }

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
			row.createCell(4).setCellValue("会员编号");
			row.createCell(5).setCellValue("会员昵称");
			row.createCell(6).setCellValue("会员卡id");
			row.createCell(7).setCellValue("会员卡名字");
			row.createCell(8).setCellValue("会员卡价格");
			row.createCell(9).setCellValue("购买数量");
			row.createCell(10).setCellValue("赠送金币/张");
			row.createCell(11).setCellValue("赠送积分/张");
			row.createCell(12).setCellValue("赠送会员时间/张");
			row.createCell(13).setCellValue("总价");
			row.createCell(14).setCellValue("购买ip地址");
			row.createCell(15).setCellValue("下单时间");
			row.createCell(16).setCellValue("发货时间");
			rowid++;
		}
		for (MemberOrder order : list) {
			XSSFRow row = spreadsheet.createRow(rowid);
			row.createCell(0).setCellValue(order.getOut_trade_no());
			row.createCell(1).setCellValue(order.getPay_type());
			row.createCell(2).setCellValue(order.getTransaction_id());
			row.createCell(3).setCellValue(order.getStatus());
			row.createCell(4).setCellValue(order.getMemberId());
			row.createCell(5).setCellValue(order.getNickname());
			row.createCell(6).setCellValue(order.getClubCardId());
			row.createCell(7).setCellValue(order.getClubCardName());
			row.createCell(8).setCellValue(order.getClubCardPrice());
			row.createCell(9).setCellValue(order.getNumber());
			row.createCell(10).setCellValue(order.getGold());
			row.createCell(11).setCellValue(order.getScore());
			if (order.getVipTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("DDD天");
				Date date = new Date(order.getVipTime());
				row.createCell(12).setCellValue(format.format(date));
			}
			row.createCell(13).setCellValue(order.getTotalamount());
			row.createCell(14).setCellValue(order.getReqIP());
			if (order.getCreateTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(order.getCreateTime());
				row.createCell(15).setCellValue(format.format(date));
			}
			if (order.getDeliveTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(order.getDeliveTime());
				row.createCell(16).setCellValue(format.format(date));
			}
			rowid++;
		}
	}
}
