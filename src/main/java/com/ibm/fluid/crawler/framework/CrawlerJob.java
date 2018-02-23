package com.ibm.fluid.crawler.framework;

import java.util.List;
import java.util.Map;

public interface CrawlerJob {
	public void setUp(Map<String,String> config);
	public List<CrawlerTask> getTasks();
	public void setJobId(String jobId);
	public String getJobId();
	public boolean hasMoreTasks();
}
