package com.ibm.fluid.crawler.exception;

public class BaseCrawlerException extends Exception {

	private static final long serialVersionUID = 8096752862600625697L;

	private final String message;

	private final Throwable throwable;


	public BaseCrawlerException(String message, Throwable t) {
		super(message, t);
		this.message = message;
		this.throwable = t;
	}
	
	public BaseCrawlerException(String message) {
		super(message);
		this.message = message;
		this.throwable = null;
	}
	
	public Throwable getThrowable() {
		return throwable;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
