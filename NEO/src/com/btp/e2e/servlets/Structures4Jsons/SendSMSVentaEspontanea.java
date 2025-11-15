package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class SendSMSVentaEspontanea extends PojoPrimary {

	private String terminal = "";
	private String telefono = "";
	private String id_transaccion = "";
	private String cod_validacion_sms = "";
	private String latitud = "";
	private String longitud = "";
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getId_transaccion() {
		return id_transaccion;
	}
	public void setId_transaccion(String id_transaccion) {
		this.id_transaccion = id_transaccion;
	}
	public String getCod_validacion_sms() {
		return cod_validacion_sms;
	}
	public void setCod_validacion_sms(String cod_validacion_sms) {
		this.cod_validacion_sms = cod_validacion_sms;
	}
	


}
