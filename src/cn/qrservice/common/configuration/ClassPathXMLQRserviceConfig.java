package cn.qrservice.common.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
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
	//生成位置
	private String dirLocation=this.getClass().getResource("/").getPath();
	//文件名格式
	private String fileNameFormat="{0}";
	//生成宽度width
	private Integer width=40;
	//生产高度 
	private Integer height=40;
	//日志
	Logger logger = Logger.getLogger(this.getClass());//日志
	/**
	 * 接收classpath下的XML文件
	 * @param fileName
	 * @throws IOException 
	 */
	public ClassPathXMLQRserviceConfig(String fileName) throws IOException {
		logger.info("Creating configuration with " + fileName);
		//尝试读取
		InputStream xmlStream = ClassPathXMLQRserviceConfig.class.getClassLoader().getResourceAsStream(fileName);
		//判断文件是否存在
		if(xmlStream==null){
			throw new FileNotFoundException(fileName);
		}
		//读取配置信息
		loadConfig(xmlStream);
		logger.info("Successfully loaded QRconfig.");
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

		//读配置
		List<Element> list = root.elements();
		for (Element e : list) {
			if(e.getName().equals("QRservice")){
				List<Element> list1 = e.elements();
				for(Element e1 : list1){
					if(e1.getName().equals("dirLocation")){
						dirLocation = e1.getStringValue();
					}else if(e1.getName().equals("fileNameFormat")){
						fileNameFormat = e1.getStringValue();
					}else if(e1.getName().equals("width")){
						width = Integer.parseInt(e1.getStringValue());
					}else if(e1.getName().equals("height")){
						height = Integer.parseInt(e1.getStringValue());
					}
				}
			}
		}
	}

	public String getDirLocation() {
		return dirLocation;
	}

	public void setDirLocation(String dirLocation) {
		this.dirLocation = dirLocation;
	}

	public String getFileNameFormat() {
		return fileNameFormat;
	}

	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
