package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class MaterialOrder extends PojoPrimary {
	private String matnr = "";
	private String cantidad = "";
	private String monto = "";
	private String oc = "";
	private List<String> cupones = new ArrayList<String>();

	public String getMatnr() {
		
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getCantidad() {
		
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getMonto() {
		
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
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
