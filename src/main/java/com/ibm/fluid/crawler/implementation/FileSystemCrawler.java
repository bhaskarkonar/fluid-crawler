package com.ibm.fluid.crawler.implementation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.framework.CrawlerTask;

public class FileSystemCrawler extends AbstractCrawlerJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrawlerJob.class);
	private Map<String, String> fileCrawlConfig;
	private List<String> listOfDirectories;

	@Override
	public void setUp(Map<String, String> config) {
		this.fileCrawlConfig = config;
	}

	@Override
	public List<CrawlerTask> getTasks() {

		if (null == listOfDirectories) {

			try {
				int maxDepth = (fileCrawlConfig.get(Constants.FILESYSTEM_MAX_DEPTH) != null)
						? Integer.parseInt(fileCrawlConfig.get(Constants.FILESYSTEM_MAX_DEPTH))
						: 0;
				Stream<Path> streamPath = (maxDepth > 0)
						? Files.walk(Paths.get(this.fileCrawlConfig.get(Constants.DIR_NAME)), maxDepth)
						: Files.walk(Paths.get(this.fileCrawlConfig.get(Constants.DIR_NAME)));
				listOfDirectories = streamPath.filter(Files::isDirectory).map(p -> p.toString())
						.collect(Collectors.toList());

			} catch (IOException e) {
				LOGGER.error("Error parsing directory:" + Paths.get(this.fileCrawlConfig.get(Constants.DIR_NAME)), e);
			}

		}
		String inputFilePattern = fileCrawlConfig.get(Constants.FILESYSTEM_REGEX_PATERN);
		FileFilter isFileFilter = (File file) -> {
			return file.isFile() && file.getName().matches(inputFilePattern);
		};

		LOGGER.info("List of directory:"+listOfDirectories);
		File dir = new File(this.listOfDirectories.get(0));
		this.listOfDirectories.remove(0);

		File[] files = dir.listFiles(isFileFilter);
		if (files.length == 0) {
			LOGGER.warn("There is no files in directory " + dir);
		}

		if (this.listOfDirectories.isEmpty()) {
			allTasksRetrived();
		}

		List<CrawlerTask> taskList=new ArrayList<>();
		for(File f:files) {
			
				Map<String, String> taskConfig = new HashMap<>();
				taskConfig.put(Constants.FILE_NAME, f.toString());
				CrawlerTask fsTask = (CrawlerTask) getApplicationContext().getBean(Constants.FILESYSTEM_TASK_BEAN,
						Double.toString(Math.random()), taskConfig);
				fsTask.setJobId(getJobId());
				taskList.add(fsTask);
			
		}
		LOGGER.info("No. of tasks created:"+taskList.size());
		return taskList;
	}

}
