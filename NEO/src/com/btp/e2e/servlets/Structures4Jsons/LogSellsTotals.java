package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class LogSellsTotals extends PojoPrimary {
	private String total = "";
	private String cantidadVentas = "";
	private List<DetalleTipoVentaSCP> listDetalleTipoVenta = new ArrayList<DetalleTipoVentaSCP>();

	public String getCountSells() {
		
		return cantidadVentas;
	}

	public void setCountSells(String countSells) {
		this.cantidadVentas = countSells;
	}

	public String getTotal() {
		
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<DetalleTipoVentaSCP> getListDetalleTipoVenta() {
		
		return listDetalleTipoVenta;
	}

	public void setListDetalleTipoVenta(List<DetalleTipoVentaSCP> listDetalleTipoVenta) {
		this.listDetalleTipoVenta = listDetalleTipoVenta;
	}

}
