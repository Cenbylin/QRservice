package cn.qrservice.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.qrservice.common.utils.imagewriter.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRserviceTest {
	
	
	public void testWrite(){
		try {
		     String content = "http://www.baidu.com/";
		     String path = "D:/testImage/";
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     //配置项
		     Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object>();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     //产生位矩阵
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
		     //nio指定文件
		     Path file = Paths.get(path, "index.jpg");
		     //矩阵转图片文件
		     MatrixToImageWriter.writeToPath(bitMatrix, "jpg", file);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	
	public void testGetPath(){
		System.out.println(QRserviceTest.class.getResource("/").getPath());
	}
	
	public void numChangeTest(){
		String string = "1";
		System.out.println((int)(Object)string);
		//不可行
		//用toString在Integer.parse
	}
	
	
	public void getFlieName(){
		System.out.println("lalala{0}".replace("{0}", String.valueOf(1)));
	}
	@Test
	public void getFileDe(){
		System.out.println(java.io.File.separatorChar);
	}
	
}
