package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class BaseOdataServiceGET extends PojoPrimary {
	private OdataResults d = new OdataResults();

	public OdataResults getD() {
		
		return d;
	}

	public void setD(OdataResults d) {
		this.d = d;
	}
}
