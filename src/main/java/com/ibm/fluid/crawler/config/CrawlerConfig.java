/**
 * 
 */
package com.ibm.fluid.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.ibm.fluid.crawler.implementation.local.CrawlManager;

/**
 * @author BHASKARKONAR
 */
@Import(FluidConfig.class)
@PropertySource("classpath:application.properties")
@PropertySource(value = "file:${CONFIG_DIR}/${CONFIG_FILENAME}", ignoreResourceNotFound = true)
@PropertySource(value = "file:${CLI_CONFIG_FILE}")

@Configuration
@ComponentScan(value = { "com.ibm.fluid.crawler.implementation" })

public class CrawlerConfig {

	@Bean(name = "crawMgr")
	public CrawlManager getFluidCrawler() {
		return new CrawlManager();
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
