package com.btp.e2e.servlets.Structures4Jsons;

import java.util.Iterator;
import java.util.List;

import com.btp.e2e.entities.ClaseMensaje;
import com.btp.e2e.implementations.PojoPrimary;
import com.google.gson.Gson;

public class ResponseActivateGuide extends PojoPrimary {

	private boolean resultado = false;
	private boolean resultadoSLL = false;
	private String mensaje = "";
	private String respuestaSAP = "";
	private String respuestaSLL = "";
	private String parametrosEnviados = "";
	private String respuestaXML = "";
	private String id_turno = "";

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

	public ResponseActivateGuide() {

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

	public boolean isResultadoSLL() {
		
		return resultadoSLL;
	}

	public void setResultadoSLL(boolean resultadoSLL) {
		this.resultadoSLL = resultadoSLL;
	}

	public String getRespuestaSAP() {
		
		return respuestaSAP;
	}

	public void setRespuestaSAP(String respuestaSAP) {
		// this.respuestaSAP = respuestaSAP;
	}

	public String getRespuestaSLL() {
		
		return respuestaSLL;
	}

	public void setRespuestaSLL(String respuestaSLL) {
		this.respuestaSLL = respuestaSLL;
	}

	public String getId_turno() {
		
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public String getParametrosEnviados() {
		
		return parametrosEnviados;
	}

	public void setParametrosEnviados(String parametrosEnviados) {
		this.parametrosEnviados = parametrosEnviados;
	}

	public String getRespuestaXML() {
		
		return respuestaXML;
	}

	public void setRespuestaXML(String respuestaXML) {
		this.respuestaXML = respuestaXML;
	}

}
