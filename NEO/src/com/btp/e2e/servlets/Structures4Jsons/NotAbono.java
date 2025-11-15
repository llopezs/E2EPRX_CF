package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class NotAbono extends PojoPrimary {
	private String rut;
	private String struct;

	public String getRut() {
		
		return rut;
	}

	public void setRut(String value) {
		this.rut = value;
	}

	public String getStruct() {
		
		return struct;
	}

	public void setStruct(String value) {
		this.struct = value;
	}
}
