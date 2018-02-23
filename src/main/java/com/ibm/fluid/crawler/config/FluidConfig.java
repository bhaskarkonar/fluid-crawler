package com.ibm.fluid.crawler.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.ibm.fluid.crawler.framework.CrawlerJob;
import com.ibm.fluid.crawler.framework.CrawlerTask;
import com.ibm.fluid.crawler.framework.FluidCrawler;
import com.ibm.fluid.crawler.implementation.FileSystemCrawler;
import com.ibm.fluid.crawler.implementation.FileSystemCrawlerTask;
import com.ibm.fluid.crawler.implementation.FluidCrawlerEngine;

@Configuration
@ComponentScan(value = { "com.ibm.fluid.crawler.implementation", "com.ibm.fluid.crawler.implementation.local" })
@PropertySource("classpath:application.properties")
public class FluidConfig {

	@Scope("prototype")
	@Bean(name = Constants.FILESYSTEM_CRAWLER_BEAN)
	public CrawlerJob getDir1Crawler(String directory, String maxDepth, String fileRegexPattern) {
		CrawlerJob fileSysCrawler = new FileSystemCrawler();
		Map<String, String> config = new HashMap<>();
		config.put(Constants.DIR_NAME, directory);
		config.put(Constants.FILESYSTEM_MAX_DEPTH, maxDepth);
		config.put(Constants.FILESYSTEM_REGEX_PATERN, fileRegexPattern);
		fileSysCrawler.setUp(config);
		return fileSysCrawler;
	}

	@Scope("prototype")
	@Bean(name = Constants.FILESYSTEM_TASK_BEAN)
	@Lazy(value = true)
	public CrawlerTask getTask(String taskId, Map<String, Object> taskConfig) {
		return new FileSystemCrawlerTask(taskId, taskConfig);
	}

	@Bean(name = "crawlerEngine")
	public FluidCrawler getInsightReCrawler() {
		return new FluidCrawlerEngine();
	}

	@Bean
	public Validator getValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

}
