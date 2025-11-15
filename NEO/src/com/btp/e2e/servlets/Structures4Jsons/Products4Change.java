package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class Products4Change extends PojoPrimary {
	private String producto = "";
	private String precio_uni = "";
	private String cantidad = "";

	public String getProducto() {
		
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getPrecio_uni() {
		
		return precio_uni;
	}

	public void setPrecio_uni(String precio_uni) {
		this.precio_uni = precio_uni;
	}

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
}
