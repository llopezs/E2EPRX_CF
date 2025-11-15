package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogRecepcionesSAP extends PojoPrimary {

	private String id;
	private String servicio;
	private String datos;
	private String sy_origen;
	private String sy_destino;
	private String respuesta;
	private String status_http;
	private String timestamp_start;
	private String timestamp_end;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServicio() {
		
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getDatos() {
		
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	public String getSy_origen() {
		
		return sy_origen;
	}

	public void setSy_origen(String sy_origen) {
		this.sy_origen = sy_origen;
	}

	public String getTimestamp_start() {
		
		return timestamp_start;
	}

	public void setTimestamp_start(String timestamp_start) {
		this.timestamp_start = timestamp_start;
	}

	public String getRespuesta() {
		
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getSy_destino() {
		
		return sy_destino;
	}

	public void setSy_destino(String sy_destino) {
		this.sy_destino = sy_destino;
	}

	public String getStatus_http() {
		
		return status_http;
	}

	public void setStatus_http(String status_http) {
		this.status_http = status_http;
	}

	public String getTimestamp_end() {
		
		return timestamp_end;
	}

	public void setTimestamp_end(String timestamp_end) {
		this.timestamp_end = timestamp_end;
	}

}
