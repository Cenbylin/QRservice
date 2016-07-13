package cn.qrservice.codefactory.task;

import java.io.File;
import java.util.List;

import cn.qrservice.common.configuration.AbstractConfig;

/**
 * 任务内容分发库--主要解处理并发任务获取
 * @author Cenby7
 *
 */
public class TaskContentProvider {
	private List<String> contents;
	private int currentNum;
	private int amount;
	private String fileDir;
	//配置
	private AbstractConfig cfg;
	
	/**
	 * 传入内容，构造任务提供者
	 * @param contents
	 */
	public TaskContentProvider(List<String> contents, AbstractConfig cfg){
		this.contents = contents;
		amount = contents.size();
		this.cfg = cfg;
		//初始化创建目录
		String fileDir = cfg.getDirLocation() + File.separatorChar + System.currentTimeMillis();
		try {
			if (!(new File(fileDir).isDirectory())) {
			    new File(fileDir).mkdirs();
			}
		} catch (Exception e) {
			
		}
		this.fileDir = fileDir;
		
	}
	
	/**
	 * 获得TaskContent
	 * @return
	 */
	public TaskContent getContent(){
		int index;
		synchronized (this) {
			//取得索引
			index = currentNum++;
		}
		//判断是否超过上限
		if(index>=amount){
			return null;
		}
		System.err.println(index);
		return new TaskContent(index, contents.get(index));
	}
	
	/**
	 * 工具，生成文件名（可拓展）
	 * @param index
	 * @return
	 */
	public String getFileName(int index){
		return cfg.getFileNameFormat().replace("{0}", String.valueOf(index))+".jpg";
	}
	
	/**
	 * 工具，获得目标目录
	 * @return
	 */
	public String getDir(){
		return fileDir;
	}
	
	/**
	 * 工具，获得宽度
	 * @return
	 */
	public int getWidth(){
		return cfg.getWidth();
	}
	
	/**
	 * 工具，获得高度
	 * @return
	 */
	public int getHeight(){
		return cfg.getHeight();
	}
}
