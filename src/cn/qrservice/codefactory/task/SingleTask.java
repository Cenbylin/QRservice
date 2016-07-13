package cn.qrservice.codefactory.task;

import java.util.List;

import cn.qrservice.codefactory.support.TaskCallBack;
import cn.qrservice.common.configuration.AbstractConfig;

/**
 * 对应一个任务，一批二维码待生产
 * @author Cenby7
 *
 */
public class SingleTask extends Thread {
	//配置
	AbstractConfig cfg;
	//内容集合
	List<String> contents;
	//回调对象
	TaskCallBack callBack;
	//任务分发器
	TaskContentProvider taskContentProvider;
	
	public SingleTask(AbstractConfig cfg, List<String> contents,
			TaskCallBack callBack) {
		super();
		this.cfg = cfg;
		this.contents = contents;
		this.callBack = callBack;
		//初始化动作
		taskContentProvider = new TaskContentProvider(contents, cfg);
	}

	/**
	 * 一个批量任务处理
	 */
	@Override
	public void run() {
		CodeMaker codeMaker = new CodeMaker(taskContentProvider);
		codeMaker.start();
	}
	
}
