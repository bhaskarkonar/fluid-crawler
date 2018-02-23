package com.ibm.fluid.crawler.implementation.local.kafka.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;

public class CrawlerVO implements Serializable {

	private static final long serialVersionUID = 5346075185378822830L;

	private transient JsonNode content;

	private FilesystemVO filesystemVO;

	private DatabaseVO databaseVO;

	private DocumentumVO documentumVO;

	public JsonNode getContent() {
		return content;
	}

	public void setContent(JsonNode content) {
		this.content = content;
	}

	public FilesystemVO getFilesystemVO() {
		return filesystemVO;
	}

	public void setFilesystemVO(FilesystemVO filesystemVO) {
		this.filesystemVO = filesystemVO;
	}

	public DatabaseVO getDatabaseVO() {
		return databaseVO;
	}

	public void setDatabaseVO(DatabaseVO databaseVO) {
		this.databaseVO = databaseVO;
	}

	public DocumentumVO getDocumentumVO() {
		return documentumVO;
	}

	public void setDocumentumVO(DocumentumVO documentumVO) {
		this.documentumVO = documentumVO;
	}

}
