package com.ibm.fluid.crawler.implementation.local.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "applicationId", "dataType", "pipelineId", "accessPolicy", "contentType", "contentSource",
		"crawlDate", "retentionPolicy", "contentVersion", "uniqueId", "crawlerService", "sessionID" })

public class Crawler {

	@JsonProperty("applicationId")
	private String applicationId;
	@JsonProperty("dataType")
	private String dataType;
	@JsonProperty("pipelineId")
	private String pipelineId;
	@JsonProperty("accessPolicy")
	private String accessPolicy;
	@JsonProperty("contentType")
	private String contentType;
	@JsonProperty("contentSource")
	private ContentSource contentSource;
	@JsonProperty("crawlDate")
	private String crawlDate;
	@JsonProperty("retentionPolicy")
	private String retentionPolicy;
	@JsonProperty("contentVersion")
	private String contentVersion;
	@JsonProperty("uniqueId")
	private String uniqueId;
	@JsonProperty("crawlerService")
	private String crawlerService;
	@JsonProperty("sessionID")
	private String sessionID;

	@JsonProperty("sessionID")
	public String getSessionID() {
		return sessionID;
	}

	@JsonProperty("sessionID")
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}

	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("dataType")
	public String getDataType() {
		return dataType;
	}

	@JsonProperty("dataType")
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@JsonProperty("pipelineId")
	public String getPipelineId() {
		return pipelineId;
	}

	@JsonProperty("pipelineId")
	public void setPipelineId(String pipelineId) {
		this.pipelineId = pipelineId;
	}

	@JsonProperty("accessPolicy")
	public String getAccessPolicy() {
		return accessPolicy;
	}

	@JsonProperty("accessPolicy")
	public void setAccessPolicy(String accessPolicy) {
		this.accessPolicy = accessPolicy;
	}

	@JsonProperty("contentType")
	public String getContentType() {
		return contentType;
	}

	@JsonProperty("contentType")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@JsonProperty("contentSource")
	public ContentSource getContentSource() {
		return contentSource;
	}

	@JsonProperty("contentSource")
	public void setContentSource(ContentSource contentSource) {
		this.contentSource = contentSource;
	}

	@JsonProperty("crawlDate")
	public String getCrawlDate() {
		return crawlDate;
	}

	@JsonProperty("crawlDate")
	public void setCrawlDate(String crawlDate) {
		this.crawlDate = crawlDate;
	}

	@JsonProperty("retentionPolicy")
	public String getRetentionPolicy() {
		return retentionPolicy;
	}

	@JsonProperty("retentionPolicy")
	public void setRetentionPolicy(String retentionPolicy) {
		this.retentionPolicy = retentionPolicy;
	}

	@JsonProperty("contentVersion")
	public String getContentVersion() {
		return contentVersion;
	}

	@JsonProperty("contentVersion")
	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}

	@JsonProperty("uniqueId")
	public String getUniqueId() {
		return uniqueId;
	}

	@JsonProperty("uniqueId")
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@JsonProperty("crawlerService")
	public String getCrawlerService() {
		return crawlerService;
	}

	@JsonProperty("crawlerService")
	public void setCrawlerService(String crawlerService) {
		this.crawlerService = crawlerService;
	}

}
