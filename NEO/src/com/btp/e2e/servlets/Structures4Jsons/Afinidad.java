package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class Afinidad extends PojoPrimary {
	private String rut = "";
	private String estado = "";
	private String montodescuento = "";
	private String codigoconvenio = "";
	private String observacion = "";

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEstado() {
		
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMontodescuento() {
		
		return montodescuento;
	}

	public void setMontodescuento(String montodescuento) {
		this.montodescuento = montodescuento;
	}

	public String getCodigoconvenio() {
		
		return codigoconvenio;
	}

	public void setCodigoconvenio(String codigoconvenio) {
		this.codigoconvenio = codigoconvenio;
	}

	public String getObservacion() {
		
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
