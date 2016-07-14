package cn.qrservice.codefactory.task;

import java.io.File;
import java.util.List;

import cn.qrservice.codefactory.support.TaskCallBack;
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
	private String foldName;
	private TaskCallBack callBack;
	//配置
	private AbstractConfig cfg;
	
	/**
	 * 传入内容，构造任务提供者
	 * @param contents
	 */
	public TaskContentProvider(List<String> contents, AbstractConfig cfg, TaskCallBack callBack){
		this.callBack = callBack;
		this.contents = contents;
		amount = contents.size();
		this.cfg = cfg;
		//初始化创建目录
		String foldName = cfg.getDirLocation() + File.separatorChar + System.currentTimeMillis();
		try {
			if (!(new File(foldName).isDirectory())) {
			    new File(foldName).mkdirs();
			}
		} catch (Exception e) {
			
		}
		this.foldName = foldName;
		
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
			//回调
			callBack.taskFinish(foldName, null);
			return null;
		}
		return new TaskContent(index, contents.get(index));
	}
	
	/**
	 * 工具，生成文件名（可拓展）
	 * @param index
	 * @return
	 */
	public String getFileName(int index){
		return cfg.getFileNameFormat().replace("{0}", String.valueOf(index+1))+".jpg";
	}
	
	/**
	 * 工具，获得目标目录
	 * @return
	 */
	public String getFoldName(){
		return foldName;
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
