package com.btp.e2e.servlets;

import java.util.ArrayList;

public class ResponseSAP {
	private String RESULTS = "";
	private ArrayList<String> MSGS = new ArrayList<String>();
	private String TO_ID = "";

	public String getRESULTS() {
		return RESULTS;
	}

	public void setRESULTS(String rESULTS) {
		RESULTS = rESULTS;
	}

	public ArrayList<String> getMSGS() {
		return MSGS;
	}

	public void setMSGS(ArrayList<String> mSGS) {
		MSGS = mSGS;
	}

	public String getTO_ID() {
		return TO_ID;
	}

	public void setTO_ID(String tO_ID) {
		TO_ID = tO_ID;
	}

}
