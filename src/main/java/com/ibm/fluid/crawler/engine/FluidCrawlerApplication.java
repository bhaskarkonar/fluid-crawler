package com.ibm.fluid.crawler.engine;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.config.CrawlerConfig;
import com.ibm.fluid.crawler.framework.CrawlerJob;
import com.ibm.fluid.crawler.framework.FluidCrawler;
import com.ibm.fluid.crawler.implementation.local.CrawlManager;

/**
 * This is the entry point of the crawler application. It accepts --config-file
 * parameter which is the file path of the config file that will drive this
 * application.
 * 
 * @author BHASKARKONAR
 */
//@SpringBootApplication
public class FluidCrawlerApplication implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(FluidCrawlerApplication.class);

	@Autowired
	Validator validator;

	/**
	 * 
	 * 
	 * @author BHASKARKONAR
	 * @param args
	 */
	public static void main(String... args) {
		SpringApplication.run(FluidCrawlerApplication.class, args);
		/*
		 * AbstractApplicationContext context=new
		 * AnnotationConfigApplicationContext(FluidConfig.class);
		 * crawler.addJob((FileSystemCrawler) context.getBean("fileCrawl1"));
		 * crawler.addJob((FileSystemCrawler) context.getBean("fileCrawl2"));
		 * crawler.start(); context.close();
		 */ }

	@Override
	public void run(ApplicationArguments appArgs) throws Exception {
		logger.info("Application started with command-line arguments: {}", Arrays.toString(appArgs.getSourceArgs()));

		boolean containsConfigFileOption = appArgs.containsOption("config-file");
		String cliConfigFileName = "";
		if (containsConfigFileOption) {
			cliConfigFileName = appArgs.getOptionValues("config-file").get(0);

			logger.info("Using config file location passed. File location:" + cliConfigFileName);
		}

		System.setProperty("CLI_CONFIG_FILE", cliConfigFileName);
		System.setProperty("CONFIG_DIR", Constants.CONFIG_DIR);
		System.setProperty("CONFIG_FILENAME", Constants.CONFIG_FILENAME);

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(CrawlerConfig.class);
		CrawlManager cm = (CrawlManager) context.getBean("crawMgr");

		Set<ConstraintViolation<CrawlManager>> validationResults = validator.validate(cm);
		for (ConstraintViolation<CrawlManager> valRes : validationResults) {
			logger.error(valRes.getMessage());
		}
		if (validationResults.size() > 0) {
			logger.error("Application failed to start because of validation error");
			System.exit(1);
		} else {
			FluidCrawler crawler = (FluidCrawler) context.getBean("crawlerEngine");
			for (CrawlerJob cj : cm.getCrawlerList()) {
				crawler.addJob(cj);
			}
			crawler.start();
			context.close();

		}

	}

}
