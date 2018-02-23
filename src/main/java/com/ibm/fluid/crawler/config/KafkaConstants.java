package com.ibm.fluid.crawler.config;

public class KafkaConstants {

	public static final String KAFKA_PROPERTY_VALUE_SERIALIZER = "value.serializer";
	public static final String KAFKA_PROPERTY_KEY_SERIALIZER = "key.serializer";
	public static final String KAFKA_PROPERTY_BUFFER_MEMORY = "buffer.memory";
	public static final String KAFKA_PROPERTY_LINGER_MS = "linger.ms";
	public static final String KAFKA_PROPERTY_BATCH_SIZE = "batch.size";
	public static final String KAFKA_PROPERTY_RETRIES = "retries";
	public static final String KAFKA_PROPERTY_ACKS = "acks";
	public static final String KAFKA_PROPERTY_BOOTSTRAP_SERVERS = "bootstrap.servers";
	public static final String KAFKA_PROPERTY_MAX_REQUEST_SIZE = "max.request.size";

	public static final String KAFKA_DATA_TIME_ZONE = "GMT";
	public static final String KAFKA_DATA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String KAFKA_DATA_VERSION_DATE_FORMAT = "yyyyMMdd";

	private KafkaConstants() {

	}

}
