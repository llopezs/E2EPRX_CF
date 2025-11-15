package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class SelectOption extends PojoPrimary {
	private String key = "";
	private String text = "";

	public String getText() {
		
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKey() {
		
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
