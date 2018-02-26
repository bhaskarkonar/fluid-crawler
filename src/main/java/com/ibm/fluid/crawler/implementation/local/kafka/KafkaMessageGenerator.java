/**
 * 
 */
package com.ibm.fluid.crawler.implementation.local.kafka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.config.KafkaConstants;
import com.ibm.fluid.crawler.exception.CrawlerGenericException;
import com.ibm.fluid.crawler.framework.result.TaskResult;
import com.ibm.fluid.crawler.implementation.local.kafka.model.ContentSource;
import com.ibm.fluid.crawler.implementation.local.kafka.model.Crawler;
import com.ibm.fluid.crawler.implementation.local.kafka.model.KafkaMessage;
import com.ibm.fluid.crawler.implementation.local.kafka.model.MetaInfo;
import com.ibm.fluid.crawler.util.Utility;

/**
 * @author BHASKARKONAR
 */
@Component
public class KafkaMessageGenerator {
	
	Logger logger=LoggerFactory.getLogger(KafkaMessageGenerator.class);

	@Value("${app.crawler.output.kafka.application.id}")
	private String appId;
	@Value("${app.crawler.output.kafka.service}")
	private String service;
	@Value("${app.crawler.output.kafka.datatype}")
	private String dataType;
	@Value("${app.crawler.output.kafka.file.content.type}")
	private String contentType;
	@Value("${app.crawler.output.kafka.data.ingestion}")
	private String dataIngestion;
	@Value("${app.crawler.output.kafka.retention.policy}")
	private String retentionPolicy;
	@Value("${app.crawler.output.kafka.access.policy}")
	private String accessPolicy;

	public KafkaMessage generateMessage(TaskResult taskResult) throws CrawlerGenericException {
		KafkaMessage kafkaMsg = new KafkaMessage();

		kafkaMsg.setMetaInfo(new MetaInfo());
		kafkaMsg.getMetaInfo().setCrawler(new Crawler());
		kafkaMsg.getMetaInfo().getCrawler().setApplicationId(appId);
		kafkaMsg.getMetaInfo().getCrawler().setAccessPolicy(accessPolicy);
		kafkaMsg.getMetaInfo().getCrawler().setContentType(contentType);

		ContentSource contentSource = new ContentSource();
		contentSource.setFileName((String) taskResult.getDataAsKeyValueList().get(Constants.FILE_NAME));
		contentSource.setContentUrl((String) taskResult.getDataAsKeyValueList().get(Constants.FILE_PATH));
		contentSource.setContentBase64((String) taskResult.getDataAsKeyValueList().get(Constants.FILE_CONTENT));
		kafkaMsg.getMetaInfo().getCrawler().setContentSource(contentSource);

		DateFormat dateformat = new SimpleDateFormat(KafkaConstants.KAFKA_DATA_DATE_FORMAT);
		dateformat.setTimeZone(TimeZone.getTimeZone(KafkaConstants.KAFKA_DATA_TIME_ZONE));
		kafkaMsg.getMetaInfo().getCrawler().setCrawlDate(dateformat.format(new Date()));

		kafkaMsg.getMetaInfo().getCrawler().setRetentionPolicy(retentionPolicy);
		kafkaMsg.getMetaInfo().getCrawler().setRetentionPolicy(retentionPolicy);
		dateformat = new SimpleDateFormat(KafkaConstants.KAFKA_DATA_VERSION_DATE_FORMAT);
		kafkaMsg.getMetaInfo().getCrawler().setContentVersion(dateformat.format(new Date()));
		kafkaMsg.getMetaInfo().getCrawler().setUniqueId(
				Utility.generateUniqueID((String) taskResult.getDataAsKeyValueList().get(Constants.FILE_PATH)));
		kafkaMsg.getMetaInfo().getCrawler().setCrawlerService(service);
		String crawlSessionID = String.valueOf(System.currentTimeMillis());
		kafkaMsg.getMetaInfo().getCrawler().setSessionID(crawlSessionID);

		logger.debug("Kafka message generated:"+Utility.generateJSONMessage(kafkaMsg));
		return kafkaMsg;
	}

}
