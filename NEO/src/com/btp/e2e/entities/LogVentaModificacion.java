package com.btp.e2e.entities;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;
import com.btp.e2e.servlets.Structures4Jsons.DetalleVenta;

public class LogVentaModificacion extends PojoPrimary {

	public LogVentaModificacion() {
		super();

	}

	private String guia = "";
	private String rut = "";
	private String fecha = "";
	private String hora = "";
	// private String detallepago = "";
	private List<DetalleVenta> detallepago = new ArrayList<DetalleVenta>();
	private String timestamp = "";
	private String id_externo = "";

	public String getGuia() {
		
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getFecha() {
		
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public List<DetalleVenta> getDetallepago() {
		return detallepago;
	}

	public void setDetallepago(List<DetalleVenta> detallepago) {
		this.detallepago = detallepago;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getId_externo() {
		
		return id_externo;
	}

	public void setId_externo(String id_externo) {
		this.id_externo = id_externo;
	}
}
