/**
 * 
 */
package com.ibm.fluid.crawler.implementation.local.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.KafkaConstants;

/**
 * @author BHASKARKONAR
 */
@Component
public class KafkaManager {
	private Producer<String, String> producer = null;
	private Properties props;

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

	public void sendMessage(String topic, String message, String id, String applicationID) {
		getProducer().send(new ProducerRecord<String, String>(topic, id, message),
				new KafkaCallback(id, applicationID));
		;
		// producer.send(arg0)
	}

	private Producer<String, String> getProducer() {
		props = new Properties();
		props.put(KafkaConstants.KAFKA_PROPERTY_BOOTSTRAP_SERVERS, bootstrapServer);
		props.put(KafkaConstants.KAFKA_PROPERTY_ACKS, acks);
		props.put(KafkaConstants.KAFKA_PROPERTY_RETRIES, priorityRetries);
		props.put(KafkaConstants.KAFKA_PROPERTY_BATCH_SIZE, batchSize);
		props.put(KafkaConstants.KAFKA_PROPERTY_LINGER_MS, lingerMs);
		props.put(KafkaConstants.KAFKA_PROPERTY_BUFFER_MEMORY, bufferMemory);
		props.put(KafkaConstants.KAFKA_PROPERTY_KEY_SERIALIZER, keySerializer);
		props.put(KafkaConstants.KAFKA_PROPERTY_VALUE_SERIALIZER, valueSerializer);
		props.put(KafkaConstants.KAFKA_PROPERTY_MAX_REQUEST_SIZE, maxRequestSize);

		return producer = new KafkaProducer<>(props);
	}

}
