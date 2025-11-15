package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RespuestaSLLWSCambiarEstadoPedido extends PojoPrimary {
	private RowSetSLLWSCambiarEstadoPedido ROWSET = new RowSetSLLWSCambiarEstadoPedido();

	public RowSetSLLWSCambiarEstadoPedido getROWSET() {
		
		return ROWSET;
	}

	public void setROWSET(RowSetSLLWSCambiarEstadoPedido rOWSET) {
		ROWSET = rOWSET;
	}
}