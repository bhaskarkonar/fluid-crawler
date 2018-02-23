package com.ibm.fluid.crawler.implementation.local.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "EDMS", "DOCSEARCH" })

public class ContentSource {

	@JsonProperty("EDMS")
	private String eDMS;
	@JsonProperty("DOCSEARCH")
	private String dOCSEARCH;
	@JsonProperty("fileName")
	private String fileName;
	@JsonProperty("contentUrl")
	private String contentUrl;

	@JsonProperty("EDMS")
	public String getEDMS() {
		return eDMS;
	}

	@JsonProperty("EDMS")
	public void setEDMS(String eDMS) {
		this.eDMS = eDMS;
	}

	@JsonProperty("DOCSEARCH")
	public String getDOCSEARCH() {
		return dOCSEARCH;
	}

	@JsonProperty("DOCSEARCH")
	public void setDOCSEARCH(String dOCSEARCH) {
		this.dOCSEARCH = dOCSEARCH;
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
