package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class DetalleMediosPago extends PojoPrimary {
	private String efectivo = "";
	private String cheque = "";
	private String tarjeta = "";
	private String prepagado = "";
	private String porDefecto = "";
	private String cantidadVentasEfectivo = "";
	private String cantidadVentasCheque = "";
	private String cantidadVentasTarjeta = "";
	private String cantidadVentasPrepagado = "";
	private String cantidadVentasPorDefecto = "";

	public String getCantidadVentasEfectivo() {
		
		return cantidadVentasEfectivo;
	}

	public void setCantidadVentasEfectivo(String cantidadVentasEfectivo) {
		this.cantidadVentasEfectivo = cantidadVentasEfectivo;
	}

	public String getCantidadVentasCheque() {
		
		return cantidadVentasCheque;
	}

	public void setCantidadVentasCheque(String cantidadVentasCheque) {
		this.cantidadVentasCheque = cantidadVentasCheque;
	}

	public String getCantidadVentasTarjeta() {
		
		return cantidadVentasTarjeta;
	}

	public void setCantidadVentasTarjeta(String cantidadVentasTarjeta) {
		this.cantidadVentasTarjeta = cantidadVentasTarjeta;
	}

	public String getCantidadVentasPrepagado() {
		
		return cantidadVentasPrepagado;
	}

	public void setCantidadVentasPrepagado(String cantidadVentasPrepagado) {
		this.cantidadVentasPrepagado = cantidadVentasPrepagado;
	}

	public String getEfectivo() {
		
		return efectivo;
	}

	public void setEfectivo(String efectivo) {
		this.efectivo = efectivo;
	}

	public String getCheque() {
		
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getTarjeta() {
		
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getPrepagado() {
		
		return prepagado;
	}

	public void setPrepagado(String prepagado) {
		this.prepagado = prepagado;
	}

	public String getCantidadVentasPorDefecto() {
		
		return cantidadVentasPorDefecto;
	}

	public void setCantidadVentasPorDefecto(String cantidadVentasPorDefecto) {
		this.cantidadVentasPorDefecto = cantidadVentasPorDefecto;
	}

	public String getPorDefecto() {
		
		return porDefecto;
	}

	public void setPorDefecto(String porDefecto) {
		this.porDefecto = porDefecto;
	}

}
