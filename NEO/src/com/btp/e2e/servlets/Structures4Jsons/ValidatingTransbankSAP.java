package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ValidatingTransbankSAP extends PojoPrimary {
	private String terminal = "";
	private String sociedad = "";
	private String transbank = "";
	private String centro = "";

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getSociedad() {
		
		return sociedad;
	}

	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	public String getTransbank() {
		
		return transbank;
	}

	public void setTransbank(String transbank) {
		this.transbank = transbank;
	}

	public String getCentro() {
		
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}
}
