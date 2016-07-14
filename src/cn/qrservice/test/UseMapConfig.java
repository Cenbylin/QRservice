package cn.qrservice.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qrservice.codefactory.support.QRExecutor;
import cn.qrservice.codefactory.support.TaskCallBack;
import cn.qrservice.common.configuration.AbstractConfig;
import cn.qrservice.common.configuration.MapQRserviceConfig;

public class UseMapConfig {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dirLocation", "D:\\qrimg");
		map.put("fileNameFormat", "{0}、测试");
		map.put("width", 140);
		map.put("height", 140);
		AbstractConfig cfg = new MapQRserviceConfig(map);
		List<String> content = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			content.add(i+"");
		}
		QRExecutor.execute(cfg, content, new TaskCallBack() {
			@Override
			public void taskFinish(String foldName, List<Integer> indexs) {
				System.out.println(foldName);
			}});
	}
}
