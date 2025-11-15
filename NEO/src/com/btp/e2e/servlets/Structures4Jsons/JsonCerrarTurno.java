package com.btp.e2e.servlets.Structures4Jsons;

import java.util.Map;

import com.btp.e2e.implementations.PojoPrimary;

public class JsonCerrarTurno extends PojoPrimary {
	private String rut;
	private String terminal;
	private String turnoSelected;

	public String getRut() {
		
		return rut;
	}

	public void setRut(String value) {
		this.rut = value;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String value) {
		this.terminal = value;
	}

	public String getTurnoSelected() {
		
		return turnoSelected;
	}

	public void setTurnoSelected(String value) {
		this.turnoSelected = value;
	}
}
