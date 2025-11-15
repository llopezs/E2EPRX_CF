package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RowSetSLLWSCambiarEstadoPedido extends PojoPrimary {
	private String PRC = "";
	private ParametersSLLWSCambiarEstadoPedido PARAMETERS = new ParametersSLLWSCambiarEstadoPedido();

	public String getPRC() {
		
		return PRC;
	}

	public void setPRC(String pRC) {
		PRC = pRC;
	}

	public ParametersSLLWSCambiarEstadoPedido getPARAMETERS() {
		
		return PARAMETERS;
	}

	public void setPARAMETERS(ParametersSLLWSCambiarEstadoPedido pARAMETERS) {
		PARAMETERS = pARAMETERS;
	}
}