package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class DiscountOrder extends PojoPrimary {
	private String descuento = "0";
	private String monto = "0";

	public String getDescuento() {
		
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getMonto() {
		
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

}
