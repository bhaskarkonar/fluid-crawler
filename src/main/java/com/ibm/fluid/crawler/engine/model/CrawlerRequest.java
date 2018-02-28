package com.ibm.fluid.crawler.engine.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrawlerRequest {
	
	@JsonProperty("crawlType")
	private String crawlType;
	@JsonProperty("crawlType")
	public String getCrawlType() {
		return this.crawlType;
	}
	@JsonProperty("crawlType")
	public void setCrawlType(String crawlType) {
		this.crawlType = crawlType;
	}
	@JsonProperty("directoryList")
	private ArrayList<String> directoryList;
	@JsonProperty("directoryList")
	public ArrayList<String> getDirectoryList() {
		return this.directoryList;
	}
	@JsonProperty("directoryList")
	public void setDirectoryList(ArrayList<String> directoryList) {
		this.directoryList = directoryList;
	}
	@JsonProperty("fileExtentions")
	private ArrayList<String> fileExtentions;
	@JsonProperty("fileExtentions")
	public ArrayList<String> getFileExtentions() {
		return this.fileExtentions;
	}
	@JsonProperty("fileExtentions")
	public void setFileExtentions(ArrayList<String> fileExtentions) {
		this.fileExtentions = fileExtentions;
	}
	@JsonProperty("maxDepth")
	private int maxDepth;
	@JsonProperty("maxDepth")
	public int getMaxDepth() {
		return this.maxDepth;
	}
	@JsonProperty("maxDepth")
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	@JsonProperty("completionDir")
	private String completionDir;
	@JsonProperty("completionDir")
	public String getCompletionDir() {
		return this.completionDir;
	}
	@JsonProperty("completionDir")
	public void setCompletionDir(String completionDir) {
		this.completionDir = completionDir;
	}
	@JsonProperty("dbType")
	private String dbType;
	@JsonProperty("dbType")
	public String getDbType() {
		return this.dbType;
	}
	@JsonProperty("dbType")
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	@JsonProperty("queryList")
	private ArrayList<String> queryList;
	@JsonProperty("queryList")
	public ArrayList<String> getQueryList() {
		return this.queryList;
	}
	@JsonProperty("queryList")
	public void setQueryList(ArrayList<String> queryList) {
		this.queryList = queryList;
	}
	@JsonProperty("maxRecords")
	private Integer maxRecords;
	@JsonProperty("maxRecords")
	public Integer getMaxRecords() {
		return this.maxRecords;
	}
	@JsonProperty("maxRecords")
	public void setMaxRecords(Integer maxRecords) {
		this.maxRecords = maxRecords;
	}
	@JsonProperty("dbName")
	private String dbName;
	@JsonProperty("dbName")
	public String getDbName() {
		return this.dbName;
	}
	@JsonProperty("dbName")
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	@JsonProperty("connectionDetails")
	private ConnectionDetails connectionDetails;
	@JsonProperty("connectionDetails")
	public ConnectionDetails getConnectionDetails() {
		return this.connectionDetails;
	}
	@JsonProperty("connectionDetails")
	public void setConnectionDetails(ConnectionDetails connectionDetails) {
		this.connectionDetails = connectionDetails;
	}
	
    @Override
    public String toString()
    {
        return "ClassPojo [maxDepth = "+maxDepth+", completionDir = "+completionDir+", directoryList = "+directoryList+", crawlType = "+crawlType+", fileExtentions = "+fileExtentions+", dbName = "+dbName+", connectionDetails = "+connectionDetails+"]";
    }
}
