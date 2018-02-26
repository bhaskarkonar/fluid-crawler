/**
 * 
 */
package com.ibm.fluid.crawler.implementation.local;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.exception.CrawlerGenericException;
import com.ibm.fluid.crawler.framework.events.TaskCompletedEvent;
import com.ibm.fluid.crawler.implementation.FileSystemCrawler;
import com.ibm.fluid.crawler.implementation.local.kafka.KafkaManager;
import com.ibm.fluid.crawler.implementation.local.kafka.KafkaMessageGenerator;
import com.ibm.fluid.crawler.implementation.local.kafka.model.KafkaMessage;
import com.ibm.fluid.crawler.util.Utility;

/**
 * @author BHASKARKONAR
 */

@Component
public class CrawlManager implements ApplicationContextAware {

	@Autowired
	private KafkaManager kafkaManager;
	@Autowired
	private KafkaMessageGenerator kafkaMsgGen;

	Logger logger = LoggerFactory.getLogger(CrawlManager.class);
	private ApplicationContext context;
	@Size(min = 1, message = "Please provide a file in the config app.crawler.file.locations")
	@Value("${app.crawler.input.file.locations:}")
	String[] fileDirsToBeCrawled;
	@Pattern(regexp = "\\s*|\\d+", message = "app.crawler.file.max-depth need to be an integer")
	@Value("${app.crawler.input.file.max-depth:0}")
	String maxDepth;
	@Value("${app.crawler.input.file.regex:}")
	String fileRegexPattern;

	@NotNull(message = "Please provide the kafka topic in the config app.crawler.topic.kafka.ingestion")
	@Value("${app.crawler.topic.kafka.ingestion}")
	String topic;

	@NotNull(message = "Please provide the application id in the config app.crawler.output.kafka.application.id")
	@Value("${app.crawler.output.kafka.application.id}")
	String applicationId;

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

	@EventListener
	public void onEvent(TaskCompletedEvent event) {
		logger.info("Task Id completed: " + event.getTaskId());
		logger.trace("Task completed with data:" + event.getTaskResult().getData());
		if (null == event.getTaskResult()
				|| null == event.getTaskResult().getDataAsKeyValueList().get(Constants.FILE_PATH)) {
			logger.error("Failed to execute task:" + event.getTaskId());
			return;
		}
		try {
			KafkaMessage kafkaMsg = kafkaMsgGen.generateMessage(event.getTaskResult());
			Future<RecordMetadata> kafkaFuture = kafkaManager.sendMessage(topic, Utility.generateJSONMessage(kafkaMsg),
					Utility.generateKafkaMessageID(kafkaMsg), applicationId);
			try {
				kafkaFuture.get();
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error while sending request to Kafka",e);
			}
		} catch (CrawlerGenericException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
