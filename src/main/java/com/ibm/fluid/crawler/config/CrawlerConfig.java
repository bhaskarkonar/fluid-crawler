/**
 * 
 */
package com.ibm.fluid.crawler.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.ibm.fluid.crawler.implementation.local.CrawlManager;

/**
 * @author BHASKARKONAR
 */
@Import(FluidConfig.class)
@PropertySource("classpath:application.properties")
@PropertySource(value = "file:${CONFIG_DIR}/${CONFIG_FILENAME}", ignoreResourceNotFound = true)
@PropertySource(value = "file:${CLI_CONFIG_FILE}", ignoreResourceNotFound = true)

@Configuration
@ComponentScan(value = { "com.ibm.fluid.crawler.implementation" })

public class CrawlerConfig {
	
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

	
	private Properties props;

	@Bean(name = "crawMgr")
	public CrawlManager getFluidCrawler() {
		return new CrawlManager();
	}
	
	@Scope("singleton")
	@Lazy(true)
	@Bean(name = "kafkaProducer")
	public KafkaProducer<String,String> getProducer() {
		KafkaProducer<String,String> producer;
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
			producer=new KafkaProducer<>(props);
		return producer;
	}

	/*
	 * @Bean public Validator getValidator() {
	 * 
	 * javax.validation.Configuration<?> config =
	 * Validation.byDefaultProvider().configure(); ValidatorFactory factory =
	 * config.buildValidatorFactory(); Validator validator = factory.getValidator();
	 * factory.close(); return validator;
	 * 
	 * }
	 */
}
