package com.btp.e2e.servlets.Structures4Jsons;

import java.util.Iterator;
import java.util.List;

import com.btp.e2e.entities.ClaseMensaje;
import com.btp.e2e.implementations.PojoPrimary;
import com.google.gson.Gson;

public class Response extends PojoPrimary {

	private boolean resultado = false;
	private String mensaje = "";
	private String wasID = "";

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

	public Response() {

	}

	public void setMensaje(List<ClaseMensaje> mensajes, String id_exerno) {
		ClaseMensaje cm = new ClaseMensaje();
		Iterator<ClaseMensaje> iterator = mensajes.iterator();
		while ((cm = iterator.next()) != null && !(cm.getId_externo().equals(id_exerno))) {
		}
		if (cm.getId_externo().equals(id_exerno)) {
			mensaje = cm.getMensaje();
		} else {
			mensaje = "MENSAJE NO CARGADO (PITS).";
		}
	}

	public String getWasID() {
		
		return wasID;
	}

	public void setWasID(String wasID) {
		this.wasID = wasID;
	}

}
