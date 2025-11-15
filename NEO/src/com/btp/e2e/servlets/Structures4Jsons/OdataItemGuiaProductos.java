package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class OdataItemGuiaProductos extends PojoPrimary {
	private String RESULT = "";
	private List<MSG> MSGS = new ArrayList<MSG>();
	private List<OdataItemGuiaProducto> ITEMS = new ArrayList<OdataItemGuiaProducto>();

	public String getRESULT() {
		
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public List<MSG> getMSGS() {
		
		return MSGS;
	}

	public void setMSGS(List<MSG> mSGS) {
		MSGS = mSGS;
	}

	public List<OdataItemGuiaProducto> getITEMS() {
		
		return ITEMS;
	}

	public void setITEMS(List<OdataItemGuiaProducto> iTEMS) {
		ITEMS = iTEMS;
	}

}
