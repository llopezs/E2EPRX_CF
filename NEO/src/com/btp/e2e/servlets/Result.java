package com.btp.e2e.servlets;

public class Result {
	private Metadata metadata;
	private String classPrx;
	private String content;
	private String file;
	private String method;
	private String userid;
	private String item;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata value) {
		this.metadata = value;
	}

	public String getClassPrx() {
		return classPrx;
	}

	public void setClassPrx(String value) {
		this.classPrx = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String value) {
		this.content = value;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String value) {
		this.file = value;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String value) {
		this.method = value;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String value) {
		this.userid = value;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String value) {
		this.item = value;
	}
}