package com.ibm.fluid.crawler.framework.result;

import java.util.Map;

public class TaskResultImpl implements TaskResult{
	
	private String taskId;
	private Object data;
	private Map<String, Object> dataAsKeyValueList;
	
	public TaskResultImpl(String taskId,Object data) {
		this.taskId=taskId;
		this.data=data;
	}

	@Override
	public String getTaskId() {
		return this.taskId;
	}

	@Override
	public Object getData() {
		return this.data;
	}

	@Override
	public void setData(Object data) {
		this.data=data;
	}

	@Override
	public Map<String, Object> getDataAsKeyValueList() {
		return this.dataAsKeyValueList;
	}

	@Override
	public void setDataAsKeyValueList(Map<String, Object> dataAsKeyValueList) {
		this.dataAsKeyValueList=dataAsKeyValueList;
	}



}
