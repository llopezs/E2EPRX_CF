package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class JsonUpdateGuide extends PojoPrimary {
	private String folioguia = "";
	private String terminal = "";
	private String id_turno = "";

	public String getFolioguia() {
		
		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getId_turno() {
		
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}
}
