package com.ibm.fluid.crawler.exception;

public class CrawlerGenericException extends BaseCrawlerException {

	private static final long serialVersionUID = -9036380475447590478L;

	public CrawlerGenericException(String message, Throwable t) {
		super(message, t);
	}

	public CrawlerGenericException(String message) {
		super(message);
	}
}
