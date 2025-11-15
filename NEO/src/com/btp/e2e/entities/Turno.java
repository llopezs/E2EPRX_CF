package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class Turno extends PojoPrimary {

	public Turno() {
		super();

	}

	private String id = "";
	private String rut = "";
	private String terminal = "";
	private String vence = "";
	private String status = "";
	private String timestamp = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getVence() {
		
		return vence;
	}

	public void setVence(String vence) {
		this.vence = vence;
	}

	public String getStatus() {
		
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
