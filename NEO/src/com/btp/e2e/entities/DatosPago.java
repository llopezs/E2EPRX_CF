package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class DatosPago extends PojoPrimary {
	private String total_pedido;
	private String descuento_pedido;
	private String monto_prepagado;
	private String total_pagar;
	private String monto_descuento_dev_garantia;
	private String cant_dev_garantia;
	private String cod_condicion;
	private String descuento_condicion;
	private String monto_descuento_cilindro;
	private String monto_descuento_gas;
	private String monto_difvta;
	private String codigo_descuento;
	private String orden_compra;

	public String getTotal_pedido() {
		
		return total_pedido;
	}

	public void setTotal_pedido(String total_pedido) {
		this.total_pedido = total_pedido;
	}

	public String getDescuento_pedido() {
		
		return descuento_pedido;
	}

	public void setDescuento_pedido(String descuento_pedido) {
		this.descuento_pedido = descuento_pedido;
	}

	public String getMonto_prepagado() {
		
		return monto_prepagado;
	}

	public void setMonto_prepagado(String monto_prepagado) {
		this.monto_prepagado = monto_prepagado;
	}

	public String getTotal_pagar() {
		
		return total_pagar;
	}

	public void setTotal_pagar(String total_pagar) {
		this.total_pagar = total_pagar;
	}

	public String getMonto_descuento_dev_garantia() {
		
		return monto_descuento_dev_garantia;
	}

	public void setMonto_descuento_dev_garantia(String monto_descuento_dev_garantia) {
		this.monto_descuento_dev_garantia = monto_descuento_dev_garantia;
	}

	public String getCant_dev_garantia() {
		
		return cant_dev_garantia;
	}

	public void setCant_dev_garantia(String cant_dev_garantia) {
		this.cant_dev_garantia = cant_dev_garantia;
	}

	public String getCod_condicion() {
		
		return cod_condicion;
	}

	public void setCod_condicion(String cod_condicion) {
		this.cod_condicion = cod_condicion;
	}

	public String getDescuento_condicion() {
		
		return descuento_condicion;
	}

	public void setDescuento_condicion(String descuento_condicion) {
		this.descuento_condicion = descuento_condicion;
	}

	public String getMonto_descuento_cilindro() {
		
		return monto_descuento_cilindro;
	}

	public void setMonto_descuento_cilindro(String monto_descuento_cilindro) {
		this.monto_descuento_cilindro = monto_descuento_cilindro;
	}

	public String getMonto_descuento_gas() {
		
		return monto_descuento_gas;
	}

	public void setMonto_descuento_gas(String monto_descuento_gas) {
		this.monto_descuento_gas = monto_descuento_gas;
	}

	public String getMonto_difvta() {
		
		return monto_difvta;
	}

	public void setMonto_difvta(String monto_difvta) {
		this.monto_difvta = monto_difvta;
	}

	public String getCodigo_descuento() {
		
		return codigo_descuento;
	}

	public void setCodigo_descuento(String codigo_descuento) {
		this.codigo_descuento = codigo_descuento;
	}

	public String getOrden_compra() {
		return orden_compra;
	}

	public void setOrden_compra(String orden_compra) {
		this.orden_compra = orden_compra;
	}

}
