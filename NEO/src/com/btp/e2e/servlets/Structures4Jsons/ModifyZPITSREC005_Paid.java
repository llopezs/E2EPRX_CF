package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ModifyZPITSREC005_Paid extends PojoPrimary {
	private String id_venta = "";
	private String metodo_pago_nuevo = "";
	private String transbank = "";

	public String getId_venta() {
		
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}

	public String getMetodo_pago_nuevo() {
		
		return metodo_pago_nuevo;
	}

	public void setMetodo_pago_nuevo(String metodo_pago_nuevo) {
		this.metodo_pago_nuevo = metodo_pago_nuevo;
	}

	public String getTransbank() {
		
		return transbank;
	}

	public void setTransbank(String transbank) {
		this.transbank = transbank;
	}
}
