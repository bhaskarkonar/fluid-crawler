package com.ibm.fluid.crawler.framework.result;

import java.util.Map;

public interface TaskResult {
	
	public String getTaskId();
	public Object getData();
	public void setData(Object data);
	public Map<String,Object> getDataAsKeyValueList();
	public void setDataAsKeyValueList(Map<String,Object> dataAsKeyValueList);
}
