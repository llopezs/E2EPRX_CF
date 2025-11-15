package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class SAPLogVentaErrorCatch extends PojoPrimary {
	private boolean resultado = false;
	private String mensaje = "";

	public boolean isResultado() {
		
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getMensaje() {
		
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}