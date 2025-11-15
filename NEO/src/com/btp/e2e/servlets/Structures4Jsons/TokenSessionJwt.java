package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;
import com.google.gson.Gson;

public class TokenSessionJwt extends PojoPrimary {

	private long exp;
	private String sub;
	private LoginOutData attrs;

	public long getExp() {
		 return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public String getSub() {
		 return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public LoginOutData getAttrs() {
		 return attrs;
	}

	public void setAttrs(LoginOutData attrs) {
		this.attrs = attrs;
	}

}
