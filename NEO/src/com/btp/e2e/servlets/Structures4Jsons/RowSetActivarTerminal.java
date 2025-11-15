package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RowSetActivarTerminal extends PojoPrimary {
	private RowRootActivarTerminal ROWSET = new RowRootActivarTerminal();

	public RowRootActivarTerminal getROWSET() {
		
		return ROWSET;
	}

	public void setROWSET(RowRootActivarTerminal rOWSET) {
		ROWSET = rOWSET;
	}
}
