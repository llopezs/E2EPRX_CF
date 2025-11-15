package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ParametrosInGuia extends PojoPrimary {
	private String folioguia = "";
	private String terminal = "";

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getFolioguia() {
		
		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}
}
