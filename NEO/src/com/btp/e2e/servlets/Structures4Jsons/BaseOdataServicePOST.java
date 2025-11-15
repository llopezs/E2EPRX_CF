package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class BaseOdataServicePOST extends PojoPrimary {
	private OdataResult d = new OdataResult();

	public OdataResult getD() {
		
		return d;
	}

	public void setD(OdataResult d) {
		this.d = d;
	}
}
