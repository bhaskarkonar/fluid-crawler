package com.ibm.fluid.crawler.implementation.local.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "crawler" })

public class MetaInfo {

	@JsonProperty("crawler")
	private Crawler crawler;

	@JsonProperty("crawler")
	public Crawler getCrawler() {
		return crawler;
	}

	@JsonProperty("crawler")
	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
	}

}
