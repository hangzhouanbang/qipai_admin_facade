package com.anbang.qipai.admin.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeCreateUtil {

	public static void createQrCode(String content, int qrCodeSize, String imageFormat, HttpServletResponse response)
			throws IOException, WriterException {
		// 设置二维码纠错级别ＭＡＰ
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 矫错级别
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
		// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth - 100, matrixWidth - 100, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// 使用比特矩阵画并保存图像
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i - 50, j - 50, 1, 1);
				}
			}
		}
		// 增加LOGO
		// 获取输入流
		BufferedImage imageLocal = ImageIO.read(new File("/data/app/qipai_admin_facade/logo.jpg"));
		graphics.drawImage(imageLocal, 350, 350, 200, 200, new ImageObserver() {

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		ImageIO.write(image, imageFormat, response.getOutputStream());
	}

	// 合成图片
	public static void generateImag(String imageUrl, String content, String imageFormat, OutputStream out, String extra)
			throws IOException, WriterException {
		// 获取合成图片
		HttpURLConnection conn = (HttpURLConnection) new URL(imageUrl).openConnection();
		conn.setRequestMethod("GET");
		// 获取输入流
		BufferedImage imageLocal = ImageIO.read(conn.getInputStream());
		imageLocal.createGraphics();
		// 获取二维码图片
		Graphics2D graphics = (Graphics2D) imageLocal.getGraphics();
		// 设置二维码纠错级别ＭＡＰ
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q); // 矫错级别
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 180, 180, hintMap);
		// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth = byteMatrix.getWidth();
		// graphics.setColor(Color.WHITE);
		// graphics.fillRect(30, 500, matrixWidth - 20, matrixWidth - 20);
		// 使用比特矩阵画并保存图像
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i + 20, j + 490, 1, 1);
				}
			}
		}
		// 增加LOGO
		// 获取输入流
		BufferedImage imageLocal1 = ImageIO.read(new File("/data/app/qipai_agent/logo.jpg"));
		graphics.drawImage(imageLocal1, 90, 560, 40, 40, new ImageObserver() {

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		// 填充邀请码
		// Font font = new Font("思源黑体", Font.BOLD, 40); // 定义字体对象
		// graphics.setFont(font); // 设置graphics的字体对象
		// graphics.setColor(Color.RED);
		// graphics.drawString(extra, 160, 704);
		ImageIO.write(imageLocal, imageFormat, out);
	}

}
