package cn.qrservice.codefactory.support;

import java.util.List;

import cn.qrservice.codefactory.task.SingleTask;
import cn.qrservice.common.configuration.AbstractConfig;


/**
 * 多任务
 * @author Cenby7
 *
 */
public class QRExecutor {

	/**
	 * 启动批量二维码任务
	 * @param cfg 配置项
	 * @param content 内容集合
	 * @param callBack 回调对象
	 * @return 任务对象引用
	 */
	public static SingleTask execute(AbstractConfig cfg, List<String> content, TaskCallBack callBack){
		//任务管理
		
		//新建任务
		SingleTask singleTask = new SingleTask(cfg, content, callBack);
		
		//启动任务（本质是一个线程）
		singleTask.start();
		return singleTask;
	}
}
