package com.ibm.fluid.crawler.engine;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.ibm.fluid.crawler.engine.model.CrawlerRequest;

@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
@RestController
@RequestMapping(value="/api/")
public class FluidCrawlerController {
	
	Logger log=LoggerFactory.getLogger(FluidCrawlerController.class);
	
	@RequestMapping(value="/crawl",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON)
	public ResponseEntity<String> enrollPng(@RequestBody List<CrawlerRequest> cwReq) {
		System.out.println(cwReq);
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<String>("string",status);
	}

}
