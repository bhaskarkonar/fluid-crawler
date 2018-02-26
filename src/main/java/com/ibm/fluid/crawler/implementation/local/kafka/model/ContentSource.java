package com.ibm.fluid.crawler.implementation.local.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ContentSource {

	@JsonProperty("contentBase64")
	private String contentBase64;
	@JsonProperty("fileName")
	private String fileName;
	@JsonProperty("contentUrl")
	private String contentUrl;


	@JsonProperty("contentBase64")
	public String getContentBase64() {
		return contentBase64;
	}

	@JsonProperty("contentBase64")
	public void setContentBase64(String dOCSEARCH) {
		this.contentBase64 = dOCSEARCH;
	}

	@JsonProperty("fileName")
	public String getFileName() {
		return fileName;
	}

	@JsonProperty("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty("contentUrl")
	public String getContentUrl() {
		return contentUrl;
	}

	@JsonProperty("contentUrl")
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

}
