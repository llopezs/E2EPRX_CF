package com.btp.e2e.servlets.pushNotifications;

public class DataNotification {
	private String title = "";
	private String subject = "";
	private String body = "";
	private String idpush = "";
	private String json = "";
	private int retries = 10;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIdpush() {
		return idpush;
	}

	public void setIdpush(String idpush) {
		this.idpush = idpush;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

}
