package cn.qrservice.codefactory.task;

import java.io.IOException;

import com.google.zxing.WriterException;

import cn.qrservice.common.utils.QRcreator;


public class CodeMaker extends Thread {
	TaskContentProvider taskContentProvider;
	
	/**
	 * 二维码生成器--接收一个任务内容集引用
	 * @param contents
	 */
	public CodeMaker(TaskContentProvider taskContentProvider){
		this.taskContentProvider = taskContentProvider;
	}
	
	@Override
	public void run() {
		TaskContent taskContent;
		//获取到任务即执行
		while((taskContent = taskContentProvider.getContent())!=null){
			try {
				QRcreator.createCode(taskContent.getContent(), 
						taskContentProvider.getDir(), 
						taskContentProvider.getFileName(taskContent.getIndex()), 
						taskContentProvider.getWidth(), 
						taskContentProvider.getHeight());
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
