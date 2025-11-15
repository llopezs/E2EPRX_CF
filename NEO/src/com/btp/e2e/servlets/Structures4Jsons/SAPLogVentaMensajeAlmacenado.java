package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class SAPLogVentaMensajeAlmacenado extends PojoPrimary {
	private String msg = "";
	private String MSG = "";

	public String getMsg() {
		
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMSG() {
		
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}
}
