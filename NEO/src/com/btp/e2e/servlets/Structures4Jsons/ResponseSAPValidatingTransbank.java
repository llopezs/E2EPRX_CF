package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ResponseSAPValidatingTransbank extends PojoPrimary {
	private String MSG = "";
	private String RESULT = "false";

	public String getMSG() {
		
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getRESULT() {
		
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}
}
