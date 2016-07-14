package cn.qrservice.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import cn.qrservice.codefactory.support.QRExecutor;
import cn.qrservice.codefactory.support.TaskCallBack;
import cn.qrservice.common.configuration.AbstractConfig;
import cn.qrservice.common.configuration.ClassPathXMLQRserviceConfig;

public class UseXMLConfig {
	public static void main(String[] args) {
		AbstractConfig cfg = null;
		try {
			cfg = new ClassPathXMLQRserviceConfig("qrconfig.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> content = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			content.add(i+"");
		}
		QRExecutor.execute(cfg, content, new TaskCallBack() {
			@Override
			public void taskFinish(String foldName, List<Integer> indexs) {
				System.out.println(foldName);
				try {
					//压缩
					ZipFile zipFile = new ZipFile("D:\\AddFolder.zip");
					ZipParameters parameters = new ZipParameters();
					parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
					parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
					zipFile.addFolder(foldName, parameters);
				} catch (ZipException e) {
					e.printStackTrace();
				}
			}});
	}
}
