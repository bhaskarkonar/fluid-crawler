package com.ibm.fluid.crawler.implementation.local.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KafkaCallback implements Callback {

	private static final Logger LOGGER = LogManager.getLogger(KafkaCallback.class);

	private String id;

	private String applicationID;

	public KafkaCallback(String id, String applicationID) {
		this.id = id;
		this.applicationID = applicationID;
	}

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		if (null != exception) {
			String msg = String.format("Application: %s: Error posting message with id: %s to Kafka.", applicationID,
					id);
			LOGGER.info(msg);
			LOGGER.error("Exception in pushing message to Kafka: ", exception);
		} else {
			if (null != metadata) {
				String msg = String.format(
						"Application: %s: Message posted successfully with id: %s to topic: %s partition: %s at: %s",
						applicationID, id, metadata.topic(), metadata.partition(), metadata.timestamp());
				LOGGER.info(msg);
			}
		}
	}

}
