package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RespuestaSLLWSCrearPedidoTarreo extends PojoPrimary {
	private RowSetSLLWSCrearPedidoTarreo ROWSET = new RowSetSLLWSCrearPedidoTarreo();

	public RowSetSLLWSCrearPedidoTarreo getROWSET() {
		
		return ROWSET;
	}

	public void setROWSET(RowSetSLLWSCrearPedidoTarreo rOWSET) {
		ROWSET = rOWSET;
	}
}
