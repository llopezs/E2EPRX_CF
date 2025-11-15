package com.btp.e2e.servlets.pushNotifications;

public class AndroidStructures {
	private String priority;
	private String ttl;
	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPriority() {		
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTtl() {		
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
}
