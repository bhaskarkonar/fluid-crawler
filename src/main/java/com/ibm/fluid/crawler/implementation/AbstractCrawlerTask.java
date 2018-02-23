package com.ibm.fluid.crawler.implementation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import com.ibm.fluid.crawler.framework.CrawlerTask;
import com.ibm.fluid.crawler.framework.events.TaskCompletedEvent;
import com.ibm.fluid.crawler.framework.result.TaskResult;

@Scope("prototype")
public abstract class AbstractCrawlerTask implements CrawlerTask {

	@Autowired
	ApplicationEventPublisher publisher;
	private static final Logger LOGGER=LoggerFactory.getLogger(AbstractCrawlerTask.class);
	


	public final Map<String,Object> taskConfig;
	private String jobId;
	public final String taskId;
	
	public AbstractCrawlerTask(String taskId,Map<String,Object> config){
		this.taskId=taskId;
		this.taskConfig=config;
	}
	
	
	public void setPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	public Map<String, Object> getTaskConfig() {
		return taskConfig;
	}

	public void taskCompleted(TaskResult tr){
		TaskCompletedEvent taskItemCompleted = new TaskCompletedEvent(getTaskId(), getTaskConfig(), tr);
		taskItemCompleted.setJobId(getJobId());
		LOGGER.debug("Published event with task id:"+taskItemCompleted.getTaskId());
		LOGGER.debug("Published event with task id:"+taskItemCompleted.getJobId());
		
		
		this.publisher.publishEvent(taskItemCompleted);
	}
	
	public String getTaskId() {
		return this.taskId;
	}
	
	@Override
	public String getJobId() {
		return jobId;
	}
	@Override
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
