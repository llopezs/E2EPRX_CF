package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class ResponseSAPGUIDE extends PojoPrimary {

	private String RESULT = "";
	private List<MSG> MSGS = new ArrayList<MSG>();

	public List<MSG> getMSGS() {
		
		return MSGS;
	}

	public void setMSGS(List<MSG> mSGS) {
		MSGS = mSGS;
	}

	public String getRESULT() {
		
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public boolean isResult() {
		
		return this.RESULT.equals("true");
	}
}
