package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class DetalleVenta extends PojoPrimary {
	private String medioPago = "";
	private String monto = "";

	public String getMonto() {
		
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String ncupon = "";

	public String getMedioPago() {
		
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
}