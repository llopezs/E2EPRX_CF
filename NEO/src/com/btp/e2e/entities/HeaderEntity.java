package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class HeaderEntity extends PojoPrimary {

	private String key;
	private String value;

	public String getKey() {
		
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HeaderEntity(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
