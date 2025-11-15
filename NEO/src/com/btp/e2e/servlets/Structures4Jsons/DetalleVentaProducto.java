package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class DetalleVentaProducto extends PojoPrimary {
	private String producto = "";
	private String precio = "";
	private String cantidad = "";
	private String oc = "";
	private List<String> cupones = new ArrayList<String>();

	public String getProducto() {
		
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getPrecio() {
		
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getOc() {
		
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	public List<String> getCupones() {
		
		return cupones;
	}

	public void setCupones(List<String> cupones) {
		this.cupones = cupones;
	}

}