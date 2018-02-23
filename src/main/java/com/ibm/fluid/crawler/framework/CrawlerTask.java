package com.ibm.fluid.crawler.framework;

import com.ibm.fluid.crawler.framework.result.TaskResult;

public interface CrawlerTask {
	public void setJobId(String jobId);
	public String getJobId();
	public String getTaskId();
	public TaskResult crawl();
}
