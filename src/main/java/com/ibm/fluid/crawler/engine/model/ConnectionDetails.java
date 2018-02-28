package com.ibm.fluid.crawler.engine.model;

public class ConnectionDetails {
	private String userName;

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String password;

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String host;

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private String port;

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
    @Override
    public String toString()
    {
        return "ClassPojo [port = "+port+", host = "+host+", userName = "+userName+", password = "+password+"]";
    }
}
