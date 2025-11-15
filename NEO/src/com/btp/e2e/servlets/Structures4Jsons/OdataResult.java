package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class OdataResult extends PojoPrimary {
	private OdataMetadata __metadata;
	private String Userid;
	private String Method;
	private String Item;
	private String Msg;

	public OdataMetadata get__metadata() {
		
		return __metadata;
	}

	public void set__metadata(OdataMetadata __metadata) {
		this.__metadata = __metadata;
	}

	public String getUserid() {
		
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getMethod() {
		
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public String getItem() {
		
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public String getMsg() {
		
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}
}
