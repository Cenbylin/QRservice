package cn.qrservice.codefactory.support;

import java.util.List;

/**
 * 任务回调接口
 * @author Cenby7
 *
 */
public interface TaskCallBack{
	public void taskFinish(String foldName, List<Integer> indexs);
} 