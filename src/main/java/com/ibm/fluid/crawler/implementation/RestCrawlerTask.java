package com.ibm.fluid.crawler.implementation;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.fluid.crawler.config.Constants;
import com.ibm.fluid.crawler.framework.result.TaskResult;
import com.ibm.fluid.crawler.framework.result.TaskResultImpl;

public class RestCrawlerTask extends AbstractCrawlerTask {

	public static final Logger LOGGER=LoggerFactory.getLogger(RestCrawlerTask.class);
	@Autowired(required=false)
	private CredentialsProvider provider;
	
	public RestCrawlerTask(String taskId, Map<String, Object> config) {
		super(taskId, config);
	}

	@Override
	public TaskResult crawl() {
		TaskResult tr=null;
		String responseBody=null;
		try(CloseableHttpClient httpclient = HttpClients.createDefault()){
			String crawlURL=taskConfig.get(Constants.BASE_URL)+"/"+taskConfig.get(Constants.PATH_PARAM);
			LOGGER.debug("URL crawled:"+crawlURL);
			HttpClientBuilder clientBuilder=HttpClientBuilder.create();
			if(null!=provider){
				clientBuilder.setDefaultCredentialsProvider(provider);	
			}
			
			
			HttpClient client = clientBuilder.build();
			
					
			HttpResponse response = client.execute(new HttpGet(crawlURL));
			LOGGER.debug("Status code:"+response.getStatusLine());
			if(response.getStatusLine().getStatusCode()==200){
				responseBody = EntityUtils.toString(response.getEntity());	
			}
			
			
		} catch (IOException e) {
			LOGGER.error("Task id:"+taskId,e);
		}
		
		tr=new TaskResultImpl(getTaskId(), responseBody);
		taskCompleted(tr);
		return tr;
	}

}
