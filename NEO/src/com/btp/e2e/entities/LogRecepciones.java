package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogRecepciones extends PojoPrimary {

	private String id;
	private String servicio;
	private String datos;
	private String sy_origen;
	private String timestamp;
	private String timestamp_end;
	private String respuesta;
	private String app_version;
	private String os_version;
	private String model_device;

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

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRespuesta() {
		
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getApp_version() {
		
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getOs_version() {
		
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getModel_device() {
		
		return model_device;
	}

	public void setModel_device(String model_device) {
		this.model_device = model_device;
	}

	public String getTimestamp_end() {
		
		return timestamp_end;
	}

	public void setTimestamp_end(String timestamp_end) {
		this.timestamp_end = timestamp_end;
	}

}
