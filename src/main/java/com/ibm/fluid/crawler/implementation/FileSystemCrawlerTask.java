package com.ibm.fluid.crawler.implementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.framework.result.TaskResult;
import com.ibm.fluid.crawler.framework.result.TaskResultImpl;

@Component
public class FileSystemCrawlerTask extends AbstractCrawlerTask {
	Logger logger = LoggerFactory.getLogger(FileSystemCrawler.class);

	public FileSystemCrawlerTask() {
		super("123", new HashMap<>());
	}

	public FileSystemCrawlerTask(String taskId, Map<String, Object> config) {
		super(taskId, config);
	}

	@Override
	public TaskResult crawl() {
		String fileContent = null;
		File file = null;
		Map<String, Object> data = new ConcurrentHashMap<>();
		try {
			
			fileContent = new String(Base64.getEncoder()
					.encode(Files.readAllBytes(Paths.get((String) taskConfig.get(Constants.FILE_NAME)))));
			file = new File((String) taskConfig.get(Constants.FILE_NAME));
			logger.info("Successfully crawled:"+(String) taskConfig.get(Constants.FILE_NAME));
			data.put(Constants.FILE_NAME, file.getName());
			data.put(Constants.FILE_PATH, file.getCanonicalFile().getCanonicalPath());
			data.put(Constants.FILE_CONTENT, fileContent);
		} catch (IOException e) {
			logger.error("Failed to read file for task id:" + this.getTaskId(), e);
		}

		TaskResult tr = new TaskResultImpl(getTaskId(), fileContent);
		tr.setDataAsKeyValueList(data);
		taskCompleted(tr);
		return tr;
	}

}
