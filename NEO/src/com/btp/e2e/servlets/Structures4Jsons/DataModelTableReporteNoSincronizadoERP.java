package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class DataModelTableReporteNoSincronizadoERP extends PojoPrimary {
	private String id_venta = "";
	private String terminal = "";
	private String id_turno = "";
	private String guia = "";
	private String tipo = "";
	private String fecha = "";
	private String hora = "";
	private String status = "";
	private String detalle_completo_venta = "";
	private String existeSap = "";
	private String productos = "";

	public String getId_venta() {
		
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
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

	public String getGuia() {
		
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}

	public String getTipo() {
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getStatus() {
		
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetalle_completo_venta() {
		
		return detalle_completo_venta;
	}

	public void setDetalle_completo_venta(String detalle_completo_venta) {
		this.detalle_completo_venta = detalle_completo_venta;
	}

	public String getExisteSap() {
		
		return existeSap;
	}

	public void setExisteSap(String existeSap) {
		this.existeSap = existeSap;
	}

	public String getProductos() {
		
		return productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}
}
