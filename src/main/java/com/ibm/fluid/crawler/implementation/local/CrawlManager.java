/**
 * 
 */
package com.ibm.fluid.crawler.implementation.local;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.implementation.FileSystemCrawler;

/**
 * @author BHASKARKONAR
 */
@Scope("singleton")
@Component
public class CrawlManager implements ApplicationContextAware {



	Logger logger = LoggerFactory.getLogger(CrawlManager.class);
	private ApplicationContext context;
	@Size(min = 1, message = "Please provide a file in the config app.crawler.input.file.locations")
	@Value("${app.crawler.input.file.locations:}")
	String[] fileDirsToBeCrawled;
	@Pattern(regexp = "\\s*|\\d+", message = "app.crawler.file.max-depth need to be an integer")
	@Value("${app.crawler.input.file.max-depth:0}")
	String maxDepth;
	@Value("${app.crawler.input.file.regex:}")
	String fileRegexPattern;



	public CrawlManager() {

	}

	public List<FileSystemCrawler> getCrawlerList() {

		/*
		 * kafkaManager.sendMessage(topic, "1234", "1234", applicationId); try {
		 * Thread.sleep(1000000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		List<FileSystemCrawler> fsCrawlerList = new ArrayList<>();
		for (String dir : fileDirsToBeCrawled) {
			FileSystemCrawler fsCrwlr = (FileSystemCrawler) context.getBean(Constants.FILESYSTEM_CRAWLER_BEAN, dir,
					maxDepth, fileRegexPattern);
			fsCrawlerList.add(fsCrwlr);
		}
		return fsCrawlerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext(org
	 * .springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}


}
