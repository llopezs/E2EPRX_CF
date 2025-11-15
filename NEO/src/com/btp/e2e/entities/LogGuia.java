package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogGuia extends PojoPrimary {

	public LogGuia() {
		super();

	}

	private String id = "";
	private String id_turno = "";
	private String folioguia = "";
	private String rut = "";
	private String timestamp = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_turno() {
		
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public String getFolioguia() {
		
		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
