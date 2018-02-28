/**

 * 
 */
package com.ibm.fluid.crawler.implementation.local.kafka;

import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author BHASKARKONAR
 */
@Component
public class KafkaManager {
	@Autowired
	private KafkaProducer<String,String> producer;
	private Logger logger=LoggerFactory.getLogger(KafkaManager.class);

	@Value("${app.crawler.output.kafka.bootstrap.servers}")
	private String bootstrapServer;

	@Value("${app.crawler.output.kafka.acks}")
	private String acks;

	@Value("${app.crawler.output.kafka.retries}")
	private String priorityRetries;

	@Value("${app.crawler.output.kafka.batch.size}")
	private String batchSize;

	@Value("${app.crawler.output.kafka.linger.ms}")
	private String lingerMs;

	@Value("${app.crawler.output.kafka.buffer.memory}")
	private String bufferMemory;

	@Value("${app.crawler.output.kafka.max.request.size}")
	private String maxRequestSize;

	@Value("${app.crawler.output.kafka.key.serializer}")
	private String keySerializer;

	@Value("${app.crawler.output.kafka.value.serializer}")
	private String valueSerializer;

	public Future<RecordMetadata> sendMessage(String topic, String message, String id, String applicationID) {
		logger.info("Posting message with id:"+id);
		Future<RecordMetadata> kafkaFuture = producer.send(new ProducerRecord<String, String>(topic, id, message),
				new KafkaCallback(id, applicationID));
		;
		return kafkaFuture;
	}


}
