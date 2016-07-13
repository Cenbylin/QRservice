package cn.qrservice.codefactory.task;

/**
 * 任务内容bean
 * @author Cenby7
 *
 */
public class TaskContent {
	private int index;
	private String content;
	public int getIndex() {
		return index;
	}
	public String getContent() {
		return content;
	}
	
	/**
	 * 构造函数
	 * @param index
	 * @param content
	 */
	public TaskContent(int index, String content) {
		super();
		this.index = index;
		this.content = content;
	}
	
}
