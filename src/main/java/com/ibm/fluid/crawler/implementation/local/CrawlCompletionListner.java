package com.ibm.fluid.crawler.implementation.local;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.constraints.NotNull;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.exception.CrawlerGenericException;
import com.ibm.fluid.crawler.framework.events.TaskCompletedEvent;
import com.ibm.fluid.crawler.implementation.local.kafka.KafkaManager;
import com.ibm.fluid.crawler.implementation.local.kafka.KafkaMessageGenerator;
import com.ibm.fluid.crawler.implementation.local.kafka.model.KafkaMessage;
import com.ibm.fluid.crawler.util.Utility;

@Lazy(true)
@Component
public class CrawlCompletionListner  implements ApplicationContextAware{

	@Autowired
	private KafkaManager kafkaManager;
	@Autowired
	private KafkaMessageGenerator kafkaMsgGen;
	
	@NotNull(message = "Please provide the kafka topic in the config app.crawler.topic.kafka.ingestion")
	@Value("${app.crawler.topic.kafka.ingestion}")
	String topic;

	@NotNull(message = "Please provide the application id in the config app.crawler.output.kafka.application.id")
	@Value("${app.crawler.output.kafka.application.id}")
	String applicationId;
	
	private ApplicationContext applicationContext;
	private static final Logger logger=LoggerFactory.getLogger(CrawlCompletionListner.class);
	
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	public ApplicationContext getApplicationContext() throws BeansException {
		return this.applicationContext;
	}

}
