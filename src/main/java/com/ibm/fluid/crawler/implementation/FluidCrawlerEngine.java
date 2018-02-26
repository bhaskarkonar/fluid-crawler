package com.ibm.fluid.crawler.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.fluid.crawler.framework.CrawlerJob;
import com.ibm.fluid.crawler.framework.CrawlerTask;
import com.ibm.fluid.crawler.framework.FluidCrawler;
import com.ibm.fluid.crawler.framework.result.TaskResult;


public class FluidCrawlerEngine implements FluidCrawler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(FluidCrawlerEngine.class);
	
	
	private List<CrawlerJob> jobList=new ArrayList<>();
	private ExecutorService executor;

	@PostConstruct
	public void init(){
		executor = Executors.newWorkStealingPool(4);
	}
	
	@PreDestroy
	public void destroy() throws InterruptedException{
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error("Awaiting for threads to complete interupted",e);
			throw e;
		}
		if(!executor.isShutdown()){
			LOGGER.debug("Shutting down executor");
			executor.shutdown();	
		}
	}
	
	@Override
	public void addJob(CrawlerJob task) {
		jobList.add(task);
	}

	@Override
	public void start() {
		List<Callable<TaskResult>> callableTasks = new ArrayList<>();
		for(CrawlerJob j:jobList){
			
			while(j.hasMoreTasks()){
				
				List<CrawlerTask> taskList=j.getTasks();
				

				for(CrawlerTask t:taskList){
					callableTasks.add(()->{
						TaskResult tr=null;
						try{
							tr=t.crawl();	
						}catch(Exception e){
							LOGGER.error("Task failed with id:"+t.getTaskId(),e);
						}
						return tr;
					});
					
				}
			}
			
			try {
				executor.awaitTermination(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				LOGGER.error("Awaiting for threads to complete interupted",e);
			}

			
		}
		
		try {
			LOGGER.info("Started set");
			executor.invokeAll(callableTasks);
			LOGGER.info("Completed set");
		} catch (InterruptedException e) {
			LOGGER.error("Error occured while executing threads",e);
		}


	}
	
	public void submitTask(CrawlerTask task){
		executor.submit(()->{
			TaskResult tr=null;
			try{
				tr=task.crawl();	
			}catch(Exception e){
				LOGGER.error("Task failed with id:"+task.getTaskId(),e);
			}
			return tr;
		});
	}

}
