package com.ibm.fluid.crawler.implementation.local.kafka.model;

import java.io.Serializable;

public class FilesystemVO implements Serializable {

	private static final long serialVersionUID = -1155293317136544708L;

	private String fileName;

	private String canonicalFilePath;

	private Long lastModified;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public String getCanonicalFilePath() {
		return canonicalFilePath;
	}

	public void setCanonicalFilePath(String canonicalFilePath) {
		this.canonicalFilePath = canonicalFilePath;
	}

}
