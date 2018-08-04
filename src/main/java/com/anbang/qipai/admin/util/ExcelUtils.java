package com.anbang.qipai.admin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;

public class ExcelUtils {

	public static int rowid = 0;
	public static int sheetNum = 1;

	public static void generateOrderExcel(List<MemberOrder> list, String fileName) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		File file = new File("src/main/webapp/excel");
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(file + "/" + fileName);
		XSSFSheet spreadsheet = null;
		if (rowid > 20000) {
			sheetNum++;
			spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
			rowid = 0;
		} else {
			spreadsheet = workbook.createSheet("OrderInfo" + sheetNum);
			XSSFRow row = spreadsheet.createRow(rowid);
			// Class<?> c = Order.class;
			// Field[] fields = c.getDeclaredFields();
			// int cellid = 0;
			// for (Field field : fields) {
			// String fieldname = field.getName();
			// row.createCell(cellid).setCellValue(fieldname);
			// cellid++;
			// }
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
			// Class<?> c = order.getClass();
			// Field[] fields = c.getDeclaredFields();
			// int cellid = 0;
			// for (Field field : fields) {
			// field.setAccessible(true);
			// try {
			// Object fieldvalue = field.get(order);
			// if (fieldvalue != null) {
			// row.createCell(cellid).setCellValue(fieldvalue.toString());
			// }
			// cellid++;
			// } catch (IllegalArgumentException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (IllegalAccessException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			if (order.getOut_trade_no() != null) {
				row.createCell(0).setCellValue(order.getOut_trade_no());
			}
			if (order.getPay_type() != null) {
				row.createCell(1).setCellValue(order.getPay_type());
			}
			if (order.getTransaction_id() != null) {
				row.createCell(2).setCellValue(order.getTransaction_id());
			}
			if (order.getStatus() != null) {
				row.createCell(3).setCellValue(order.getStatus());
			}
			if (order.getMemberId() != null) {
				row.createCell(4).setCellValue(order.getMemberId());
			}
			if (order.getNickname() != null) {
				row.createCell(5).setCellValue(order.getNickname());
			}
			if (order.getClubCardId() != null) {
				row.createCell(6).setCellValue(order.getClubCardId());
			}
			if (order.getClubCardName() != null) {
				row.createCell(7).setCellValue(order.getClubCardName());
			}
			if (order.getClubCardPrice() != null) {
				row.createCell(8).setCellValue(order.getClubCardPrice());
			}
			if (order.getNumber() != null) {
				row.createCell(9).setCellValue(order.getNumber());
			}
			if (order.getGold() != null) {
				row.createCell(10).setCellValue(order.getGold());
			}
			if (order.getScore() != null) {
				row.createCell(11).setCellValue(order.getScore());
			}
			if (order.getVipTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("DDD天");
				Date date = new Date(order.getVipTime());
				row.createCell(12).setCellValue(format.format(date));
			}
			if (order.getTotalamount() != null) {
				row.createCell(13).setCellValue(order.getTotalamount());
			}
			if (order.getReqIP() != null) {
				row.createCell(14).setCellValue(order.getReqIP());
			}
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
		workbook.write(out);
		workbook.close();
		out.close();
	}
}
