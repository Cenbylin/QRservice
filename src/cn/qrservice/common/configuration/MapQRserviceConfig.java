package cn.qrservice.common.configuration;

import java.util.Map;

/**
 * 从用户自组装map来创建多任务配置
 * @author Cenby7
 *
 */
public class MapQRserviceConfig implements AbstractConfig{
	//生成位置
	private String dirLocation=this.getClass().getResource("/").getPath();
	//文件名格式
	private String fileNameFormat="{0}";
	//生成宽度width
	private Integer width=40;
	//生产高度 
	private Integer height=40;
	
	/**
	 * 传入配置map方式
	 * @param cfg
	 */
	public MapQRserviceConfig(Map<String, Object> cfg) {
		//先处理
		
		//加载配置
		loadConfig(cfg);
	}
	
	/**
	 * 从map中加载配置
	 * @param cfg
	 */
	public void loadConfig(Map<String, Object> cfg){
		dirLocation = (String) (cfg.get("dirLocation")==null?dirLocation:cfg.get("dirLocation"));
		fileNameFormat = (String) (cfg.get("fileNameFormat")==null?fileNameFormat:cfg.get("fileNameFormat"));
		width = Integer.parseInt((cfg.get("width")==null?width:cfg.get("width")).toString());
		height = Integer.parseInt((cfg.get("height")==null?width:cfg.get("height")).toString());
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
