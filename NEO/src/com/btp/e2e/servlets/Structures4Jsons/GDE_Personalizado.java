package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class GDE_Personalizado extends PojoPrimary {
	
	private String patente_camion = "";
	private String patente_cisterna = "";
	private String nombre_conductor = "";
	//lt = Litros
	private String lt_descargados = "";
	private String lt_inicial = "";
	private String lt_final = "";
	private String kg_ticket = "";
	public String getPatente_camion() {
		return patente_camion;
	}
	public void setPatente_camion(String patente_camion) {
		this.patente_camion = patente_camion;
	}
	public String getPatente_cisterna() {
		return patente_cisterna;
	}
	public void setPatente_cisterna(String patente_cisterna) {
		this.patente_cisterna = patente_cisterna;
	}
	public String getNombre_conductor() {
		return nombre_conductor;
	}
	public void setNombre_conductor(String nombre_conductor) {
		this.nombre_conductor = nombre_conductor;
	}
	public String getLt_descargados() {
		return lt_descargados;
	}
	public void setLt_descargados(String lt_descargados) {
		this.lt_descargados = lt_descargados;
	}
	public String getLt_inicial() {
		return lt_inicial;
	}
	public void setLt_inicial(String lt_inicial) {
		this.lt_inicial = lt_inicial;
	}
	public String getLt_final() {
		return lt_final;
	}
	public void setLt_final(String lt_final) {
		this.lt_final = lt_final;
	}
	public String getKg_ticket() {
		return kg_ticket;
	}
	public void setKg_ticket(String kg_ticket) {
		this.kg_ticket = kg_ticket;
	}
	
	

}
