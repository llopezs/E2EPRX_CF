package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class DatosCliente extends PojoPrimary {
	private String id;
	private String id_sap;
	private String web;
	private String nombre;
	private String cluster;
	private String calle;
	private String numero;
	private String block;
	private String depto;
	private String poblacion;
	private String comuna;
	private String referencia;
	private String latitud;
	private String longitud;
	private String otros;
	private String casa;
	private String local;
	private String rut;
	private String email;

	public String getId() {

		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_sap() {

		return id_sap;
	}

	public void setId_sap(String id_sap) {
		this.id_sap = id_sap;
	}

	public String getWeb() {

		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getNombre() {

		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCluster() {
		if (this.cluster != null && this.cluster.length() > 0)
			setCluster(this.cluster.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getCalle() {

		if (this.calle != null && this.calle.length() > 0)
			setCalle(this.calle.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {

		if (this.numero != null && this.numero.length() > 0)
			setNumero(this.numero.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBlock() {

		if (this.block != null && this.block.length() > 0)
			setBlock(this.block.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getDepto() {

		if (this.depto != null && this.depto.length() > 0)
			setDepto(this.depto.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getPoblacion() {

		if (this.poblacion != null && this.poblacion.length() > 0)
			setPoblacion(this.poblacion.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getComuna() {

		if (this.comuna != null && this.comuna.length() > 0)
			setComuna(this.comuna.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getReferencia() {

		if (this.referencia != null && this.referencia.length() > 0)
			setReferencia(this.referencia.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getLatitud() {

		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {

		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getOtros() {

		if (this.otros != null && this.otros.length() > 0)
			setOtros(this.otros.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getCasa() {

		if (this.casa != null && this.casa.length() > 0)
			setCasa(this.casa.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getLocal() {

		if (this.local != null && this.local.length() > 0)
			setLocal(this.local.replaceAll("[^a-zA-Z0-9, ]+", " "));

		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getRut() {

		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {

		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void checkChars() {

		if (this.comuna != null && this.comuna.length() > 0)
			this.setComuna(this.comuna.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.poblacion != null && this.poblacion.length() > 0)
			this.setPoblacion(this.poblacion.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.calle != null && this.calle.length() > 0)
			this.setCalle(this.calle.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.numero != null && this.numero.length() > 0)
			this.setNumero(this.numero.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.depto != null && this.depto.length() > 0)
			this.setDepto(this.depto.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.casa != null && this.casa.length() > 0)
			this.setCasa(this.casa.replaceAll("[^a-zA-Z0-9, ]+", ""));
		if (this.referencia != null && this.referencia.length() > 0)
			this.setReferencia(this.referencia.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.local != null && this.local.length() > 0)
			this.setLocal(this.local.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.otros != null && this.otros.length() > 0)
			this.setOtros(this.otros.replaceAll("[^a-zA-Z0-9, ]+", " "));
		if (this.nombre != null && this.nombre.length() > 0)
			//this.setNombre(this.nombre.replaceAll("[^a-zA-Z0-9, ]+", " "));
			this.setNombre(this.nombre);

	}

}
