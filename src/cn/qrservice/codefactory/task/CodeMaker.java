package cn.qrservice.codefactory.task;


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
		
	}
	
}
