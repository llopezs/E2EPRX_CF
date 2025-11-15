package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class DetalleTipoVentaSCP extends PojoPrimary {
	private String tipo = "";
	private String montoTotal = "";
	private String cantidadVentas = "";
	private DetalleMediosPago detalleMediosPago = new DetalleMediosPago();

	public String getTipo() {
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMontoTotal() {
		
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	public DetalleMediosPago getDetalleMediosPago() {
		
		return detalleMediosPago;
	}

	public void setDetalleMediosPago(DetalleMediosPago detalleMediosPago) {
		this.detalleMediosPago = detalleMediosPago;
	}

	public String getCantidadVentas() {
		
		return cantidadVentas;
	}

	public void setCantidadVentas(String cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
}
