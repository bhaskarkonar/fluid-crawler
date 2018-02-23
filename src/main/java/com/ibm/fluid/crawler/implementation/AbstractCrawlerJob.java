package com.ibm.fluid.crawler.implementation;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;

import com.ibm.fluid.crawler.framework.CrawlerJob;
import com.ibm.fluid.crawler.framework.events.JobCompletedEvent;

public abstract class AbstractCrawlerJob implements CrawlerJob, ApplicationContextAware {

	@Autowired
	private ApplicationEventPublisher publisher;
	private boolean hasMoreTasks=true;
	private ApplicationContext applicationContext;
	private String jobId;
	
	private Map<String, String> config;
	
	@Override
	public void setUp(Map<String, String> config) {
		this.config=config;
	}

	public Map<String, String> getConfig() {
		return this.config;
	}
	
	public void jobCompleted(JobCompletedEvent jobCompleted){
		this.publisher.publishEvent(jobCompleted);
	}
	
	public boolean hasMoreTasks(){
		return this.hasMoreTasks;
	}
	public void allTasksRetrived(){
		this.hasMoreTasks=false;
	}
	public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException{
		this.applicationContext=applicationContext;
	}
	public ApplicationContext getApplicationContext(){
		return this.applicationContext;
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
