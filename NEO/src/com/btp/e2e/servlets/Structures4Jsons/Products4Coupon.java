package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class Products4Coupon extends PojoPrimary {
	private String cantidad;
	private String producto;

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String value) {
		this.cantidad = value;
	}

	public String getProducto() {
		
		return producto;
	}

	public void setProducto(String value) {
		this.producto = value;
	}
}
