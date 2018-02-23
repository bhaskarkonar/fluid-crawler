package com.ibm.fluid.crawler.framework.events;

import java.util.Map;

import com.ibm.fluid.crawler.framework.result.TaskResult;

public class TaskCompletedEvent {
	
	private Map<String,Object> taskDetails;
	private String jobId;

	private String taskId;
	private TaskResult taskResult;
	
	public TaskCompletedEvent(String taskId, Map<String,Object> taskDetails,TaskResult taskResult){
		this.taskId=taskId;
		this.taskDetails=taskDetails;
		this.taskResult=taskResult;
	}
	
	public Map<String, Object> getTaskDetails() {
		return taskDetails;
	}
	
	public String getTaskId(){
		return this.taskId;
	}
	public TaskResult getTaskResult(){
		return this.taskResult;
	}
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
