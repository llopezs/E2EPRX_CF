package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class DatosDetalle extends PojoPrimary {
	private String cantidad;
	private String item;
	private String producto;
	private String valor_unitario;
	private String valor_total;
	private String medio_pago;

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getItem() {
		
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getProducto() {
		
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getValor_unitario() {
		
		return valor_unitario;
	}

	public void setValor_unitario(String valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public String getValor_total() {
		
		return valor_total;
	}

	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}

	public String getMedio_pago() {
		
		return medio_pago;
	}

	public void setMedio_pago(String medio_pago) {
		this.medio_pago = medio_pago;
	}

}
