package com.ibm.fluid.crawler.framework.events;

import java.util.Map;

public class JobCompletedEvent {
	
	private Map<String,String> jobDetails;
	
	public JobCompletedEvent(Map<String,String> jobDetails){
		this.jobDetails=jobDetails;
	}
	
	public Map<String, String> getJobDetails() {
		return jobDetails;
	}
	
}
