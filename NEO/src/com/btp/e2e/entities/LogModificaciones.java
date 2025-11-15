package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogModificaciones extends PojoPrimary {

	public LogModificaciones() {
		super();
	}

	private String id = "";
	private String operacion = "";
	private String dato = "";
	private String name = "";
	private String email = "";
	private String scpid = "";
	private String timestamp = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperacion() {
		
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getDato() {
		
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScpid() {
		
		return scpid;
	}

	public void setScpid(String scpid) {
		this.scpid = scpid;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}