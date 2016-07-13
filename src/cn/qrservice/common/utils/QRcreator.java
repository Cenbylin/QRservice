package cn.qrservice.common.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import cn.qrservice.common.utils.imagewriter.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


/**
 * 基础工具类--产生二维码
 * @author Cenby7
 *
 */
public class QRcreator {
	private static Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object>();
	
	/**
	 * 静态代码块，初始化配置
	 */
	static{
		 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	/**
	 * 生产二维码工具类
	 * @param content
	 * @param path
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void createCode(String content, String path, String fileName, int width, int height) throws WriterException, IOException{
	     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	     //产生位矩阵
	     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	     //nio指定文件
	     Path file = Paths.get(path, fileName);
	     //矩阵转图片文件
	     MatrixToImageWriter.writeToPath(bitMatrix, "jpg", file);
	}
}
