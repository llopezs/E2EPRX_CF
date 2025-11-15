package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RowActivarTerminal extends PojoPrimary {
	private String MENSAJE_SIS = "";
	private int COD_ERROR = -1;
	private String MENSAJE_USR = "";

	public String getMENSAJE_SIS() {
		
		return MENSAJE_SIS;
	}

	public void setMENSAJE_SIS(String mENSAJE_SIS) {
		MENSAJE_SIS = mENSAJE_SIS;
	}

	public int getCOD_ERROR() {
		
		return COD_ERROR;
	}

	public void setCOD_ERROR(int cOD_ERROR) {
		COD_ERROR = cOD_ERROR;
	}

	public String getMENSAJE_USR() {
		
		return MENSAJE_USR;
	}

	public void setMENSAJE_USR(String mENSAJE_USR) {
		MENSAJE_USR = mENSAJE_USR;
	}
}
