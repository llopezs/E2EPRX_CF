package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RowSetSLLWSCrearPedidoTarreo extends PojoPrimary {
	private String PRC = "";
	private ParametersSLLWSCrearPedidoTarreo PARAMETERS = new ParametersSLLWSCrearPedidoTarreo();

	public String getPRC() {
		
		return PRC;
	}

	public void setPRC(String pRC) {
		PRC = pRC;
	}

	public ParametersSLLWSCrearPedidoTarreo getPARAMETERS() {
		
		return PARAMETERS;
	}

	public void setPARAMETERS(ParametersSLLWSCrearPedidoTarreo pARAMETERS) {
		PARAMETERS = pARAMETERS;
	}
}
