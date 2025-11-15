package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;
import com.google.gson.Gson;

public class Entity extends PojoPrimary {
	
	public String returnJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
