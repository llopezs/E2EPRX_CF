package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ProductSLL extends PojoPrimary {
	private String producto = "";
	private String cantidad = "";
	private String unidad = "";
	private String precio = "";

	public String getProducto() {
		
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getPrecio() {
		
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}
}