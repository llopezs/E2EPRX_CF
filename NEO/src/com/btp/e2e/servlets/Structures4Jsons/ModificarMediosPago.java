package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class ModificarMediosPago extends PojoPrimary {
	private String id_externo = "";
	private String rut = "";
	private String terminal = "";
	private List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();

	public String getId_externo() {
		
		return id_externo;
	}

	public void setId_externo(String id_externo) {
		this.id_externo = id_externo;
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

	public List<DetalleVenta> getDetalle() {
		
		return detalle;
	}

	public void setDetalle(List<DetalleVenta> detalle) {
		this.detalle = detalle;
	}
}
