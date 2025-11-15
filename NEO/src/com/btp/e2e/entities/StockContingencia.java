package com.btp.e2e.entities;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.servlets.Structures4Jsons.Contingencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StockContingencia {
	protected String usuario;
	protected String id_turno;
	protected String werks;
	protected String terminal;
	protected String num_oferta;
	protected String ciudad;
	protected String tlf;
	protected String direccion;
	protected List<Contingencia.Material> materials = new ArrayList<Contingencia.Material>();
	protected String lotno;
	protected String bokno;
	protected String printer;
	protected String fecha;
	protected String pm;
	protected String ind_pm;
	protected String tope_deuda;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public String getId_turno() {
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getNum_oferta() {
		return num_oferta;
	}

	public void setNum_oferta(String num_oferta) {
		this.num_oferta = num_oferta;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getBokno() {
		return bokno;
	}

	public void setBokno(String bokno) {
		this.bokno = bokno;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getInd_pm() {
		return ind_pm;
	}

	public void setInd_pm(String ind_pm) {
		this.ind_pm = ind_pm;
	}

	public String getTope_deuda() {
		return tope_deuda;
	}

	public void setTope_deuda(String tope_deuda) {
		this.tope_deuda = tope_deuda;
	}

	public List<Contingencia.Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Contingencia.Material> materials) {
		this.materials = materials;
	}



	/* MANEJO JSON */
	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public String toJSON() {
		return gson.toJson(this);
	}
	
	public static StockContingencia fromJSON(String sJSON) {
		return gson.fromJson(sJSON, StockContingencia.class);
	}
}
