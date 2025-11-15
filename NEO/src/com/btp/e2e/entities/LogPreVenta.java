package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogPreVenta extends PojoPrimary {

	public LogPreVenta() {
		super();

	}

	private String id = "";
	private String id_externo = "";
	private String servicio = "";
	private String timestamp = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_externo() {
		return id_externo;
	}

	public void setId_externo(String id_externo) {
		this.id_externo = id_externo;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
