package cn.qrservice.common.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 从classpath下加载XML文件来创建多任务配置
 * @author Cenby7
 *
 */
public class ClassPathXMLQRserviceConfig implements AbstractConfig{

	/**
	 * 接收classpath下的XML文件
	 * @param fileName
	 * @throws IOException 
	 */
	public ClassPathXMLQRserviceConfig(String fileName) throws IOException {
		//尝试读取
		InputStream xmlStream = ClassPathXMLQRserviceConfig.class.getClassLoader().getResourceAsStream(fileName);
		//判断文件是否存在
		if(xmlStream==null){
			throw new FileNotFoundException(fileName);
		}
		//读取配置信息
		loadConfig(xmlStream);
	}
	
	/**
	 * 从配置文件中装载核心信息
	 */
	@SuppressWarnings("unchecked")
	public void loadConfig(InputStream xmlStream){
		Element root = null;
		SAXReader reader = new SAXReader();
		try {
				Document doc = reader.read(xmlStream);	
				xmlStream.close();
				root = doc.getRootElement();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (DocumentException e2){
			
		}

		List<Element> list = root.elements();
		for (Element e : list) {
			if(e.getName().equals("sms")){
				List<Element> list1 = e.elements();
				for(Element e1 : list1){
					if(e1.getName().equals("ACCOUNT_SID")){
						//ACCOUNT_SID = e1.getStringValue();
					}else if(e1.getName().equals("AUTH_TOKEN")){
						//AUTH_TOKEN = e1.getStringValue();
					}else if(e1.getName().equals("APP_ID")){
						//APP_ID = e1.getStringValue();
					}
				}
			}
		}
	}

	@Override
	public String getDirLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileNameFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
